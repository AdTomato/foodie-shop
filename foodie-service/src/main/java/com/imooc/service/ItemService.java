package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CommentLevelCountsVO;
import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.ShopcatVO;

import java.util.List;
import java.util.Map;

/**
 * 商品service
 */
public interface ItemService {

    /**
     * 根据商品ID查询详情
     *
     * @param itemId 商品id
     * @return 商品详情
     * @author wangyong
     * @time 2020-07-06-00-33
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品ID查询商品图片列表
     *
     * @param itemId 商品id
     * @return 商品图片列表
     * @author wangyong
     * @time 2020-07-06-00-35
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品ID查询商品规格
     *
     * @param itemId 商品id
     * @return 商品规格
     * @author wangyong
     * @time 2020-07-06-00-36
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品ID查询商品参数
     *
     * @param itemId 商品id
     * @return 商品参数
     * @author wangyong
     * @time 2020-07-06-00-37
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品id查询商品评价等级数量
     *
     * @param itemId 商品id
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品的评价（分页）
     *
     * @param itemId   商品id
     * @param level    评价level
     * @param page     页数
     * @param pageSize 每页数量
     * @return 商品的评价
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     *
     * @param keywords 关键字
     * @param sort     排序类型
     * @param page     页数
     * @param pageSize 每页数量
     * @return
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据分类id搜索商品列表
     *
     * @param catId    分类id
     * @param sort     排序类型
     * @param page     页数
     * @param pageSize 每页数量
     * @return
     */
    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中商品数据
     *
     * @param specIds 规格ids，使用','进行拼接
     * @return 最新的购物车商品数据
     */
    List<ShopcatVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据规格id查询商品规格
     *
     * @param specId 规格id
     * @return 商品规格
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品id获取商品的主图
     *
     * @param itemId 商品id
     * @return 商品主图url
     */
    String queryItemMainImgById(String itemId);

    /**
     * 减少库存
     * @param specId 商品规格id
     * @param buyCounts 购买数量
     */
    void decreaseItemSpecStock(String specId, int buyCounts);

}
