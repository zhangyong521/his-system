package com.zy.project.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.common.constants.Constants;
import com.zy.common.domain.SimpleUser;
import com.zy.project.system.domain.Menu;
import com.zy.project.system.dto.MenuDto;
import com.zy.project.system.mapper.MenuMapper;
import com.zy.project.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<Menu> listAllMenus(MenuDto menuDto) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(menuDto.getMenuName()), Menu.COL_MENU_NAME, menuDto.getMenuName());
        qw.eq(StringUtils.isNotBlank(menuDto.getStatus()), Menu.COL_STATUS, menuDto.getStatus());
        return this.menuMapper.selectList(qw);
    }

    @Override
    public Menu getOne(Long menuId) {
        return this.menuMapper.selectById(menuId);
    }

    @Override
    public int addMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(menuDto, menu);
        //设置创建时间创建人
        menu.setCreateBy(menuDto.getSimpleUser().getUserName());
        menu.setCreateTime(DateUtil.date());
        return this.menuMapper.insert(menu);
    }

    @Override
    public int updateMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(menuDto, menu);
        //设置修改人
        menu.setUpdateBy(menuDto.getSimpleUser().getUserName());
        return this.menuMapper.updateById(menu);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        //先删除role_menu的中间表的数据【后面再加】
        //再删除菜单或权限
        return this.menuMapper.deleteById(menuId);
    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return this.menuMapper.queryChildCountByMenuId(menuId) > 0L ? true : false;
    }

}
