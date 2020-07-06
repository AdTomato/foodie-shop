package com.imooc.mapper.my;

import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

public interface MyItemsMapper {

    List<ItemCommentVO> queryItemComments(@Param(value = "paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItems(@Param(value = "paramsMap") Map<String, Object> map);
}
