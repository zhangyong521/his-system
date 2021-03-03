package com.zy.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.project.system.domain.Menu;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;


/**
 * @author  zy
 * @date  2021/2/25 16:40
 * @description 菜单权限
 * @version 1.0
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据菜单ID查询它的子节点个数
     *
     * @param menuId
     * @return
     */
    Long queryChildCountByMenuId(Long menuId);

    /**
     * 根据角色ID查询所有选中的权限菜单ID【只查子节点的】
     *
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdsByRoleId(@Param("roleId") Long roleId);
}