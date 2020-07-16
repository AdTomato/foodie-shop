package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.mapper.my.MyOrdersMapper;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.OrderStatusExample;
import com.imooc.pojo.Orders;
import com.imooc.pojo.OrdersExample;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.MyOrdersVO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyong
 */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

    @Resource
    MyOrdersMapper myOrdersMapper;

    @Resource
    OrderStatusMapper orderStatusMapper;

    @Resource
    OrdersMapper ordersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }
        List<MyOrdersVO> ordersVOList = myOrdersMapper.queryMyOrders(map);
        return setterPageGird(ordersVOList, page);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        updateOrder.setDeliverTime(new Date());

        OrderStatusExample example = new OrderStatusExample();
        OrderStatusExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andOrderStatusEqualTo(OrderStatusEnum.WAIT_DELIVER.type);

        orderStatusMapper.updateByExampleSelective(updateOrder, example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Orders queryMyOrder(String orderId, String userId) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(orderId);
        criteria.andIsDeleteEqualTo(YesOrNo.NO.type);
        List<Orders> ordersList = ordersMapper.selectByExample(example);
        return CollectionUtils.isEmpty(ordersList) ? null : ordersList.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateReceiveOrderStatus(String orderId) {
        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        updateOrder.setSuccessTime(new Date());

        OrderStatusExample example = new OrderStatusExample();
        OrderStatusExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andOrderStatusEqualTo(OrderStatusEnum.WAIT_RECEIVE.type);

        int result = orderStatusMapper.updateByExampleSelective(updateOrder, example);

        return result == 1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteOrder(String orderId, String userId) {
        Orders updateOrder = new Orders();
        updateOrder.setIsDelete(YesOrNo.YES.type);
        updateOrder.setUpdatedTime(new Date());

        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(orderId);
        criteria.andUserIdEqualTo(userId);
        int result = ordersMapper.updateByExampleSelective(updateOrder, example);
        return result == 1;
    }

    private PagedGridResult setterPageGird(List<?> list, Integer page) {
        PageInfo<?> pageInfo = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageInfo.getPages());
        grid.setRecords(pageInfo.getTotal());
        return grid;
    }
}
