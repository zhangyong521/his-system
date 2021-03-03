package com.zy.project.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.constants.Constants;
import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.Role;
import com.zy.project.system.dto.RoleDto;
import com.zy.project.system.mapper.RoleMapper;
import com.zy.project.system.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/3 14:34
 * @description TODO
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public DataGridView listRolePage(RoleDto roleDto) {
        Page<Role> page = new Page<>(roleDto.getPageNum(), roleDto.getPageSize());
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(roleDto.getRoleName()), Role.COL_ROLE_NAME, roleDto.getRoleName());
        qw.like(StringUtils.isNotBlank(roleDto.getRoleCode()), Role.COL_ROLE_CODE, roleDto.getRoleCode());
        qw.eq(StringUtils.isNotBlank(roleDto.getStatus()), Role.COL_STATUS, roleDto.getStatus());
        qw.ge(null != roleDto.getBeginTime(), Role.COL_CREATE_TIME, roleDto.getBeginTime());
        qw.le(null != roleDto.getEndTime(), Role.COL_CREATE_TIME, roleDto.getEndTime());
        qw.orderByAsc(Role.COL_ROLE_SORT);
        this.roleMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Role> listAllRoles() {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(Role.COL_STATUS, Constants.STATUS_TRUE);
        qw.orderByAsc(Role.COL_ROLE_SORT);
        return this.roleMapper.selectList(qw);
    }

    @Override
    public Role getOne(Long roleId) {
        return this.roleMapper.selectById(roleId);
    }

    @Override
    public int addRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        //设置创建人和创建时间
        role.setCreateBy(roleDto.getSimpleUser().getUserName());
        role.setCreateTime(DateUtil.date());
        return this.roleMapper.insert(role);
    }

    @Override
    public int updateRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        //设置修改人
        role.setUpdateBy(roleDto.getSimpleUser().getUserName());
        return this.roleMapper.updateById(role);
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        List<Long> ids = Arrays.asList(roleIds);
        Role role = new Role();
        if (null != roleIds && roleIds.length > 0) {
            //还要删除sys_role_menu
            this.roleMapper.deleteRoleMenuByRoleIds(ids);
            //还要删除sys_role_user
            this.roleMapper.deleteRoleUserByRoleIds(ids);
            return this.roleMapper.deleteBatchIds(ids);
        }
        return 0;
    }

    @Override
    public void saveRoleMenu(Long roleId, Long[] menuIds) {
        //根据角色ID删除sys_role_menu的数据
        this.roleMapper.deleteRoleMenuByRoleIds(Arrays.asList(roleId));
        for (Long menuId : menuIds) {
            this.roleMapper.saveRoleMenu(roleId,menuId);
        }
    }

}
