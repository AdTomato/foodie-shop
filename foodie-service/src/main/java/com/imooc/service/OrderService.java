package com.imooc.service;

import com.imooc.bo.SubmitOrderBO;

/**
 * 订单service
 */
public interface OrderService {

    /**
     * 创建订单数据
     *
     * @param submitOrderBO 前端提交过来的订单数据
     */
    String createOrder(SubmitOrderBO submitOrderBO);

}
