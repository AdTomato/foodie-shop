package com.imooc.mapper.my;

import com.imooc.pojo.OrderStatus;
import com.imooc.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wangyong
 */
public interface MyOrdersMapper {

    /**
     * 查询用户所有订单
     *
     * @param map 筛选条件
     * @return 满足条件的用户订单信息
     */
    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查询用户订单某个状态的数量
     *
     * @param map 查询条件
     * @return 某个账单的数量
     */
    Integer getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    /**
     * 订单动向查询
     *
     * @param map 查询条件
     * @return 订单动向
     */
    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);
}
