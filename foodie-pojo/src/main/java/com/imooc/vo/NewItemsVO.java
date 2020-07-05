package com.imooc.vo;

import lombok.Data;

import java.util.List;

/**
 * 最新商品VO
 *
 * @author wangyong
 * @time 2020-07-06-00-10
 */
@Data
public class NewItemsVO {

    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;
}
