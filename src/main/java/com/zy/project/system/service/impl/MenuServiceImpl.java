package com.zy.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.common.constants.Constants;
import com.zy.common.domain.SimpleUser;
import com.zy.project.system.domain.Menu;
import com.zy.project.system.mapper.MenuMapper;
import com.zy.project.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 16:40
 * @description 菜单权限
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuTree(boolean isAdmin, SimpleUser simpleUser) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq(Menu.COL_STATUS, Constants.STATUS_TRUE);
        qw.in(Menu.COL_MENU_TYPE, Constants.MENU_TYPE_M, Constants.MENU_TYPE_C);
        qw.orderByAsc(Menu.COL_PARENT_ID);
        if (isAdmin) {
            return menuMapper.selectList(qw);
        } else {
            //根据用户id查询用户拥有的菜单信息
            return menuMapper.selectList(qw);
        }
    }

}
