package com.imooc.bo.center;

import lombok.Data;

/**
 * 商品评价的BO
 */
@Data
public class OrderItemsCommentBO {

    private String commentId;
    private String itemId;
    private String itemName;
    private String itemSpecId;
    private String itemSpecName;
    private Integer commentLevel;
    private String content;

}
