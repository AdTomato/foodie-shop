package com.imooc.service;

import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;

/**
 * @author wangyong
 * @time 2020/6/24 1:08
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return true 存在 false 不存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     *
     * @param userBO 前端传递过来的用户信息
     * @return 创建好的用户
     */
    Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 检索的结果
     */
    Users queryUserForLogin(String username, String password);

}
