package com.imooc.service;

import com.imooc.bo.AddressBO;
import com.imooc.pojo.UserAddress;

import java.util.List;

/**
 * 用户收货地址
 *
 * @author wangyong
 */
public interface AddressService {

    /**
     * 根据用户id查询用户的收获地址列表
     *
     * @param userId 用户id
     * @return 用户收货地址
     * @author wangyong
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     *
     * @param addressBO
     * @author wangyong
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * 更新用户地址
     *
     * @param addressBO
     * @author wangyong
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 根据用户id和地址id删除收货地址
     *
     * @param userId    用户id
     * @param addressId 地址id
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 修改默认地址
     *
     * @param userId    用户id
     * @param addressId 地址id
     * @author wangyong
     */
    void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 获取用户收货地址
     *
     * @param userId    用户id
     * @param addressId 地址id
     * @return 用户收货地址
     * @author wangyong
     */
    UserAddress queryUserAddress(String userId, String addressId);

}
