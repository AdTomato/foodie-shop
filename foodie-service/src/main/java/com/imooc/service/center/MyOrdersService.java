package com.imooc.service.center;

import com.imooc.pojo.Orders;
import com.imooc.utils.PagedGridResult;

/**
 * @author wangyong
 */
public interface MyOrdersService {

    /**
     * 查询我的订单列表
     *
     * @param userId      用户id
     * @param orderStatus 订单状态
     * @param page        页数
     * @param pageSize    每页的大小
     * @return 返回订单列表
     */
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /**
     * 订单状态 --> 商家发货
     *
     * @param orderId 订单id
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 查询我的订单
     *
     * @param orderId 订单id
     * @param userId  用户id
     * @return 我的订单
     */
    Orders queryMyOrder(String orderId, String userId);

    /**
     * 更新订单状态确认收货
     *
     * @param orderId 订单状态
     * @return
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单(逻辑删除)
     *
     * @param orderId 订单id
     * @param userId  用户id
     * @return
     */
    boolean deleteOrder(String orderId, String userId);

}
