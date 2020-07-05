package com.imooc.service.impl;

import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.CarouselExample;
import com.imooc.service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        CarouselExample example = new CarouselExample();
        example.setOrderByClause("sort asc");
        CarouselExample.Criteria criteria = example.createCriteria();
        criteria.andIsShowEqualTo(isShow);
        List<Carousel> carouselList = carouselMapper.selectByExample(example);
        return carouselList;
    }
}
