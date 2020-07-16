package com.imooc.mapper.my;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface MyItemsCommentsMapper {

    void saveComments(@Param("paramsMap") Map<String, Object> map);

}
