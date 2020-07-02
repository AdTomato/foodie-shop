package com.imooc.mapper;

import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsImgMapper {
    long countByExample(ItemsImgExample example);

    int deleteByExample(ItemsImgExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemsImg record);

    int insertSelective(ItemsImg record);

    List<ItemsImg> selectByExample(ItemsImgExample example);

    ItemsImg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemsImg record, @Param("example") ItemsImgExample example);

    int updateByExample(@Param("record") ItemsImg record, @Param("example") ItemsImgExample example);

    int updateByPrimaryKeySelective(ItemsImg record);

    int updateByPrimaryKey(ItemsImg record);
}