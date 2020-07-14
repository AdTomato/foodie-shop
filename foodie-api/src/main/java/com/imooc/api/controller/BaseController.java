package com.imooc.api.controller;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import sun.security.provider.PolicySpiFile;

/**
 * @author wangyong
 */
@Controller
public class BaseController {

    public static final String FOODIE_SHOPCART = "shopcart";
    public static final Integer COMMENT_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    /**
     * 支付中心调用地址
     */
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    // 微信支付成功 -> 支付中心 -> 天天吃货
    //                       |-> 回调通知url
    String payReturnUrl = "http://wangyong.natappvip.cc/orders/notifyMerchantOrderPaid";

}
