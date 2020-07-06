package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CommentLevelCountsVO;
import com.imooc.vo.ItemCommentVO;
import org.apache.tomcat.util.http.fileupload.MultipartStream;

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
}
