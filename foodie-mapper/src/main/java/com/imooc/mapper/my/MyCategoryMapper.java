package com.imooc.mapper.my;

import com.imooc.vo.CategoryVO;
import com.imooc.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

public interface MyCategoryMapper {

    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);

}
