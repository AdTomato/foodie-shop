package com.imooc.service.impl;

import com.imooc.bo.ShopcatBO;
import com.imooc.bo.SubmitOrderBO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.*;
import com.imooc.service.AddressService;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import com.imooc.vo.MerchantOrdersVO;
import com.imooc.vo.OrderVO;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangyong
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrdersMapper ordersMapper;

    @Resource
    OrderItemsMapper orderItemsMapper;

    @Resource
    OrderStatusMapper orderStatusMapper;

    @Autowired
    Sid sid;

    @Autowired
    AddressService addressService;

    @Autowired
    ItemService itemService;

    @Resource
    RedisOperator redisOperator;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderVO createOrder(SubmitOrderBO submitOrderBO, Map<Object, Object> shopcat) {

        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();

        Integer postAmount = 0;

        // 1. 创建订单数据
        Orders orders = new Orders();
        String orderId = sid.nextShort();
        orders.setId(orderId);
        orders.setUserId(userId);

        UserAddress userAddress = addressService.queryUserAddress(userId, addressId);
        if (userAddress != null) {
            orders.setReceiverName(userAddress.getReceiver());
            orders.setReceiverMobile(userAddress.getMobile());
            StringBuilder sb = new StringBuilder();
            sb.append(userAddress.getProvince()).append(" ");
            sb.append(userAddress.getCity()).append(" ");
            sb.append(userAddress.getDistrict()).append(" ");
            sb.append(userAddress.getDetail());
            orders.setReceiverAddress(sb.toString());
        }

        orders.setPostAmount(postAmount);
        orders.setPayMethod(payMethod);
        orders.setLeftMsg(leftMsg);

        orders.setIsComment(YesOrNo.NO.type);
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());

        // 2. 根据规格ids循环创建订单商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        // 商品原价累计
        Integer totalAmount = 0;
        // 优惠后的实际支付价格累计
        Integer realPayAmount = 0;
        List<ShopcatBO> toBeRemovedShopcatdList = new ArrayList<>();
        for (String itemSpecId : itemSpecIdArr) {

            //  整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCounts = 1;
            if (shopcat.containsKey(itemSpecId)) {
                ShopcatBO bo = JsonUtils.jsonToPojo(String.valueOf(shopcat.get(itemSpecId)), ShopcatBO.class);
                buyCounts = bo.getBuyCounts();
                toBeRemovedShopcatdList.add(bo);
            } else {
                continue;
            }

            // 2.1 根据规格id查询规格的具体信息，主要是价格
            ItemsSpec itemsSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCounts;

            // 2.2 根据商品id，获取商品商品信息以及商品图片
            String itemId = itemsSpec.getItemId();
            Items item = itemService.queryItemById(itemId);
            String imgUrl = itemService.queryItemMainImgById(itemId);

            // 2.3 循环保存子订单到数据库
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemsSpec.getName());
            subOrderItem.setPrice(itemsSpec.getPriceDiscount());
            subOrderItem.setBuyCounts(buyCounts);
            orderItemsMapper.insert(subOrderItem);

            // 2.4 在用户提交订单以后，规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        orders.setIsDelete(YesOrNo.NO.type);
        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        ordersMapper.insert(orders);

        // 3. 保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);

        // 4. 构建商户订单，用于传给支付中心
        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setAmount(realPayAmount + postAmount);
        merchantOrdersVO.setPayMethod(payMethod);

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setMerchantOrdersVO(merchantOrdersVO);
        orderVO.setToBeRemovedShopcatdList(toBeRemovedShopcatdList);

        return orderVO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setOrderId(orderId);
        paidStatus.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatus queryOrderStatusInfo(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void closeOrder() {
        OrderStatusExample example = new OrderStatusExample();
        OrderStatusExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> orderStatuses = orderStatusMapper.selectByExample(example);
        for (OrderStatus orderStatus : orderStatuses) {
            Date createdTime = orderStatus.getCreatedTime();
            int days = DateUtil.daysBetween(createdTime, new Date());
            if (days >= 1) {
                doCloseOrder(orderStatus.getOrderId());
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doCloseOrder(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.type);
        orderStatus.setCloseTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
}
