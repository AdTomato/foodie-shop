package com.imooc.mapper.my;

import com.imooc.vo.CategoryVO;

import java.util.List;

public interface MyCategoryMapper {

    List<CategoryVO> getSubCatList(Integer rootCatId);

}
