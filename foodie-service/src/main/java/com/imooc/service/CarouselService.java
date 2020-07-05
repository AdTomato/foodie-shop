package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * 轮播图
 *
 * @author wangyong
 * @time 2020-07-04-00-59
 */
public interface CarouselService {

    /**
     * 查询所有轮播图
     *
     * @param isShow
     * @return
     */
    List<Carousel> queryAll(Integer isShow);

}
