package com.imooc.service.center;

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

}
