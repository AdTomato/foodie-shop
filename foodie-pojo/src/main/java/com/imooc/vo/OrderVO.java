package com.imooc.vo;

import com.imooc.bo.ShopcatBO;
import lombok.Data;

import java.util.List;

/**
 * @author wangyong
 */
@Data
public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;
    private List<ShopcatBO> toBeRemovedShopcatdList;

}
