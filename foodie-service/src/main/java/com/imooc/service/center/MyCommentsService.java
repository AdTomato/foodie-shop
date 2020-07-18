package com.imooc.service.center;

import com.imooc.bo.center.CenterUserBO;
import com.imooc.bo.center.OrderItemsCommentBO;
import com.imooc.pojo.OrderItems;
import com.imooc.pojo.Users;
import com.imooc.utils.PagedGridResult;

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

    /**
     * 保存用户的评论
     *
     * @param orderId     订单id
     * @param userId      用户id
     * @param commentList 评论列表
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);

    /**
     * 我的评价查询分页
     *
     * @param userId   用户id
     * @param page     页数
     * @param pageSize 每页大小
     * @return 我的评价
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
