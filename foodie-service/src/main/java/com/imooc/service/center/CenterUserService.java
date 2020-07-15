package com.imooc.service.center;

import com.imooc.bo.center.CenterUserBO;
import com.imooc.pojo.Users;

/**
 * 用户中心service
 *
 * @author wangyong
 */
public interface CenterUserService {


    /**
     * 根据用户id查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     *
     * @param userId       用户id
     * @param centerUserBO 用户信息
     * @return 最新的用户信息
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 更新用户头像
     *
     * @param userId  用户id
     * @param faceUrl 头像url地址
     * @return 更新过后的用户
     */
    Users updateUserFace(String userId, String faceUrl);
}
