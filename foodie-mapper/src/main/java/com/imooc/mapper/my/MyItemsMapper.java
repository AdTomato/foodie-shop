package com.imooc.mapper.my;

import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.SearchItemsVO;
import com.imooc.vo.ShopcatVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MyItemsMapper {

    List<ItemCommentVO> queryItemComments(@Param(value = "paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItems(@Param(value = "paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItemsByThirdCat(@Param(value = "paramsMap") Map<String, Object> map);

    List<ShopcatVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIds);

    int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") Integer pendingCounts);
}
