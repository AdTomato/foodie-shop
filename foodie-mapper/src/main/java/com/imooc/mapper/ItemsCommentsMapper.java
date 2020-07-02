package com.imooc.mapper;

import com.imooc.pojo.ItemsComments;
import com.imooc.pojo.ItemsCommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsCommentsMapper {
    long countByExample(ItemsCommentsExample example);

    int deleteByExample(ItemsCommentsExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemsComments record);

    int insertSelective(ItemsComments record);

    List<ItemsComments> selectByExample(ItemsCommentsExample example);

    ItemsComments selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemsComments record, @Param("example") ItemsCommentsExample example);

    int updateByExample(@Param("record") ItemsComments record, @Param("example") ItemsCommentsExample example);

    int updateByPrimaryKeySelective(ItemsComments record);

    int updateByPrimaryKey(ItemsComments record);
}