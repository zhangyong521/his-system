package com.zy.project.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.project.system.domain.Role;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @author  zy
 * @date  2021/3/3 14:34
 * @description 角色信息表
 * @version 1.0
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色IDS删除sys_role_menu中间表的数据
     *
     * @param ids
     */
    void deleteRoleMenuByRoleIds(@Param("ids") List<Long> ids);

    /**
     * 根据角色IDS删除sys_role_user中间表的数据
     *
     * @param ids
     */
    void deleteRoleUserByRoleIds(@Param("ids") List<Long> ids);

    /**
     * 保存角色和菜单之关的关系
     * @param roleId
     * @param menuId
     */
    void saveRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}