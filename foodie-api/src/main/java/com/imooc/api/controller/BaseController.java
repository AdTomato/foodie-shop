package com.imooc.api.controller;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import sun.security.provider.PolicySpiFile;

import java.io.File;
import java.lang.reflect.Field;

/**
 * @author wangyong
 */
@Controller
public class BaseController {

    public static final String FOODIE_SHOPCART = "shopcart";
    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    /**
     * 支付中心调用地址
     */
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    /**
     * 微信支付成功 -> 支付中心 -> 天天吃货
     * |-> 回调通知url
     */
    String payReturnUrl = "http://wangyong.natappvip.cc/orders/notifyMerchantOrderPaid";

    /**
     * 用户上传头像的位置
     */
    public static final String IMAGE_USER_FACE_LOCATION = "E:" + File.separator + "file" + File.separator + "foodie";

}
