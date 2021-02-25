package com.zy.project.system.service;

import com.zy.common.domain.SimpleUser;
import com.zy.project.system.domain.Menu;

import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 16:40
 * @description 菜单权限
 */
public interface MenuService {

    /**
     * 查询菜单信息
     * 如查用户是超级管理员，那么查询所以菜单和权限
     * 如果用户是普通用户，那么根据用户ID关联角色和权限
     *
     * @param isAdmin    是否是超级管理员
     * @param simpleUser 如果isAdmin=true  simpleUser可以为空
     * @return
     */
    List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser);
}
