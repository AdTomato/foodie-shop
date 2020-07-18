package com.imooc.mapper.my;

import com.imooc.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MyItemsCommentsMapper {

    void saveComments(@Param("paramsMap") Map<String, Object> map);

    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}
