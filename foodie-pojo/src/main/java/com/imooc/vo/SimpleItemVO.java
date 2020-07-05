package com.imooc.vo;

import lombok.Data;

import java.util.Date;

/**
 * 6个最新商品的简单数据类型
 *
 * @author wangyong
 * @time 2020-07-06-00-09
 */
@Data
public class SimpleItemVO {

    private String itemId;
    private String itemName;
    private String itemUrl;

}
