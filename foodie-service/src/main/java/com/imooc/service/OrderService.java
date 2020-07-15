package com.imooc.service;

import com.imooc.bo.SubmitOrderBO;
import com.imooc.pojo.OrderStatus;
import com.imooc.vo.OrderVO;
import org.omg.PortableInterceptor.ServerRequestInfo;

/**
 * 订单service
 *
 * @author wangyong
 */
public interface OrderService {

    /**
     * 创建订单数据
     *
     * @param submitOrderBO 前端提交过来的订单数据
     * @return 订单VO
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 更新订单状态
     *
     * @param orderId     订单id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     *
     * @param orderId 订单id
     * @return 订单状态
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时订单
     */
    void closeOrder();

}
