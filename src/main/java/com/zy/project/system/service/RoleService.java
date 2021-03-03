package com.zy.project.system.service;

import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.Role;
import com.zy.project.system.dto.RoleDto;

import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/3 14:34
 * @description 角色信息表
 */
public interface RoleService {

    /**
     * 分页查询角色
     *
     * @param roleDto
     * @return
     */
    DataGridView listRolePage(RoleDto roleDto);

    /**
     * 查询所有可用角色
     *
     * @return
     */
    List<Role> listAllRoles();

    /**
     * 根据ID查询角色
     *
     * @param roleId
     * @return
     */
    Role getOne(Long roleId);

    /**
     * 添加一个角色
     *
     * @param roleDto
     * @return
     */
    int addRole(RoleDto roleDto);

    /**
     * 修改角色
     *
     * @param roleDto
     * @return
     */
    int updateRole(RoleDto roleDto);

    /**
     * 根据角色ID删除角色
     *
     * @param roleIds
     * @return
     */
    int deleteRoleByIds(Long[] roleIds);

    /**
     * 保存角色和菜单之间的关系
     * @param roleId
     * @param menuIds
     */
    void saveRoleMenu(Long roleId, Long[] menuIds);

}
