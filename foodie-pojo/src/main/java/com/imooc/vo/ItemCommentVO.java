package com.imooc.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用于展示商品评价的VO
 */
@Data
public class ItemCommentVO {

    private Integer commentLevel;
    private String content;
    private String sepcName;
    private Date createdTime;
    private String userFace;
    private String nickName;

}
