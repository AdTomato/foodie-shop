package com.imooc.service.impl;

import com.imooc.enums.CommentLevel;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.service.ItemService;
import com.imooc.vo.CommentLevelCountsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Resource
    ItemsCommentsMapper itemsCommentsMapper;

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normanCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts + normanCounts + badCounts;

        CommentLevelCountsVO countsVO = new CommentLevelCountsVO();
        countsVO.setTotalCounts(totalCounts);
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normanCounts);
        countsVO.setBadCounts(badCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId, Integer level) {
        ItemsCommentsExample example = new ItemsCommentsExample();
        ItemsCommentsExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        if (level != null) {
            criteria.andCommentLevelEqualTo(level);
        }
        return Math.toIntExact(itemsCommentsMapper.countByExample(example));
    }
}
