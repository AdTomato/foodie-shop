package com.imooc.service.impl;

import com.imooc.service.StuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangyong
 * @time 2020/6/20 10:34
 */
@Service
public class StuServiceImpl implements StuService {


    @Override
    public void saveParent() {

    }

    @Override
    public void saveChild() {
        saveChild1();
        int i = 1 / 0;
        saveChild2();
    }

    @Override
    public void saveChild1() {

    }

    @Override
    public void saveChild2() {

    }
}
