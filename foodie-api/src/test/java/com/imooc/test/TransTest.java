package com.imooc.test;

import com.imooc.api.ApiApplication;
import com.imooc.service.TestTransService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangyong
 * @time 2020/6/20 11:28
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApiApplication.class)
public class TransTest {

//    @Autowired
    TestTransService testTransService;

//    @Test
    public void test() {
        testTransService.testPropagationTrans();
    }

}
