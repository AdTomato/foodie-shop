package com.imooc.mapper.my;

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

}
