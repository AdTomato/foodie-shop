package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;

import java.util.List;

/**
 * 分类的service
 *
 * @author wangyong
 * @time 2020-07-05-17-26
 */
public interface CategoryService {

    /**
     * 获取所有的一级分类
     *
     * @return 一级分类
     * @author wangyong
     * @time 2020-07-05-17-27
     */
    List<Category> queryAllRootLevel();

    /**
     * 根据一级分类id查询子分类信息
     *
     * @param rootCatId 一级分类id
     * @return 子分类
     * @author wangyong
     * @time 2020-07-05-18-14
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

}
