package com.imooc.service.center;

import com.imooc.bo.center.CenterUserBO;
import com.imooc.pojo.OrderItems;
import com.imooc.pojo.Users;

import java.util.List;

/**
 * 用户中心service
 *
 * @author wangyong
 */
public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     *
     * @param orderId 订单id
     * @return 商品
     */
    List<OrderItems> queryPendingComment(String orderId);

}
