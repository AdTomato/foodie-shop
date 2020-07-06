package com.imooc.service.impl;

import com.imooc.mapper.ItemsImgMapper;
import com.imooc.mapper.ItemsMapper;
import com.imooc.mapper.ItemsParamMapper;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.pojo.*;
import com.imooc.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    ItemsMapper itemsMapper;

    @Resource
    ItemsImgMapper itemsImgMapper;

    @Resource
    ItemsParamMapper itemsParamMapper;

    @Resource
    ItemsSpecMapper itemsSpecMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        ItemsImgExample example = new ItemsImgExample();
        ItemsImgExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        ItemsSpecExample example = new ItemsSpecExample();
        ItemsSpecExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        ItemsParamExample example = new ItemsParamExample();
        ItemsParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<ItemsParam> itemsParams = itemsParamMapper.selectByExample(example);
        return itemsParams == null || itemsParams.size() == 0 ? null : itemsParams.get(0);
    }
}
