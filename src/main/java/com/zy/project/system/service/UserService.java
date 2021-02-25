package com.zy.project.system.service;


import com.zy.project.system.domain.User;

/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 16:18
 * @description 用户信息逻辑接口
 */
public interface UserService {

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return
     */
    User queryUserByPhone(String phone);

    /**
     * 根据用户ID查询用户
     * @param userId 用户编号
     * @return
     */
    User getOne(Long userId);

}
