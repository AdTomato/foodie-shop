package com.imooc.api.config;

import com.imooc.service.OrderService;
import com.imooc.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 订单的定时器
 *
 * @author wangyong
 */
@Component
public class OrderJob {

    /**
     * 使用定时任务关闭超期未支付订单，会存在弊端
     * 1. 会有时间差，程序不严谨
     * 10:39下单，11:00检查不足1小时，12:00检查，超过1小时多余39分钟
     * 2. 不支持集群
     * 单击没毛病，使用集群后，就会有多个定时任务
     * 解决方案：只是用一台计算机节点，单独用来运行所有的定时任务
     * 3. 会对数据库进行全表搜索，及其影响数据库性能。
     * 定时任务，仅仅只适用于小型轻量级项目，传统项目。
     * 后续课程会涉及到消息队列MQ->RabbitMQ,RocketMQ,Kafka,ZeroMQ...
     * 延时任务（队列）
     * 10:12下单的，未付款（10）状态，11:12分检查，如果当前状态还是10，则直接关闭订单即可
     */

    private static final Logger log = LoggerFactory.getLogger(OrderJob.class);

    @Autowired
    OrderService orderService;

    // TODO 定时任务存在弊端，后期将使用RabbitMQ来改进
//    @Scheduled(cron = "0/3 * * * * ? ")
    public void autoCloseOrder() {
        orderService.closeOrder();
        log.info("执行定时任务,当前时间为：{}", DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }

}
