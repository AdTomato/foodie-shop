package com.imooc.vo;

import lombok.Data;

/**
 * 用于展示商品搜索列表VO
 */
@Data
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    private Integer sellCount;
    private String imgUrl;
    private Integer price;

}
