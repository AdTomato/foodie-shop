package com.imooc.mapper;

import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsParamMapper {
    long countByExample(ItemsParamExample example);

    int deleteByExample(ItemsParamExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemsParam record);

    int insertSelective(ItemsParam record);

    List<ItemsParam> selectByExample(ItemsParamExample example);

    ItemsParam selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemsParam record, @Param("example") ItemsParamExample example);

    int updateByExample(@Param("record") ItemsParam record, @Param("example") ItemsParamExample example);

    int updateByPrimaryKeySelective(ItemsParam record);

    int updateByPrimaryKey(ItemsParam record);
}