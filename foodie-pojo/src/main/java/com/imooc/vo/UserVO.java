package com.imooc.vo;

import lombok.Data;

/**
 * 描述:
 *
 * @author wangyong
 */
@Data
public class UserVO {
    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String face;

    /**
     * 性别：男；女
     */
    private Integer sex;

    /**
     * 用户会话token
     */
    private String userUniqueToken;

}
