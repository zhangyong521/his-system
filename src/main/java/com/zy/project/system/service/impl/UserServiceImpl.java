package com.zy.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.project.system.domain.User;
import com.zy.project.system.mapper.UserMapper;
import com.zy.project.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 16:18
 * @description 用户信息逻辑层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByPhone(String phone) {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq(User.COL_PHONE,phone);
        User user = this.userMapper.selectOne(qw);
        return user;
    }

    @Override
    public User getOne(Long userId) {
        return this.userMapper.selectById(userId);
    }

}
