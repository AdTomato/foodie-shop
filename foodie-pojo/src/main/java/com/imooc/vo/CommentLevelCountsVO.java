package com.imooc.vo;

import lombok.Data;

/**
 * 用于展示商品评价数量的vo
 */
@Data
public class CommentLevelCountsVO {

    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;

}
