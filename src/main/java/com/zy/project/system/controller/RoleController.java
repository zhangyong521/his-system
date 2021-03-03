package com.zy.project.system.controller;

import com.zy.common.utils.ShiroSecurityUtils;
import com.zy.common.vo.AjaxResult;
import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.Role;
import com.zy.project.system.dto.RoleDto;
import com.zy.project.system.service.MenuService;
import com.zy.project.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/3 14:46
 * @description 角色管理的控制层
 */
@RestController
@RequestMapping("system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 分页查询
     */
    @GetMapping("listRoleForPage")
    public AjaxResult listRoleForPage(RoleDto roleDto){
        DataGridView gridView = this.roleService.listRolePage(roleDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 不分页面查询有效的
     */
    @GetMapping("selectAllRoles")
    public AjaxResult selectAllRoles(){
        List<Role> lists=this.roleService.listAllRoles();
        return AjaxResult.success(lists);
    }

    /**
     * 查询一个
     */
    @GetMapping("getRoleById/{roleId}")
    public AjaxResult getRoleById(@PathVariable Long roleId){
        Role role=this.roleService.getOne(roleId);
        return AjaxResult.success(role);
    }

    /**
     * 添加
     */
    @PostMapping("addRole")
    public AjaxResult addRole(@Validated RoleDto roleDto){
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.roleService.addRole(roleDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateRole")
    public AjaxResult updateRole(@Validated RoleDto roleDto){
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.roleService.updateRole(roleDto));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteRoleByIds/{roleIds}")
    public AjaxResult deleteRoleByIds(@PathVariable Long[] roleIds){
        return AjaxResult.toAjax(this.roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 根据角色ID查询菜单权限ID数据
     */
    @GetMapping("getMenuIdsByRoleId/{roleId}")
    public AjaxResult getMenuIdsByRoleId(@PathVariable Long roleId){
        List<Long> ids=this.menuService.getMenusIdsByRoleId(roleId);
        return AjaxResult.success(ids);
    }

    /**
     * 保存角色和菜单之间的关系
     */
    @PostMapping("saveRoleMenu/{roleId}/{menuIds}")
    public AjaxResult saveRoleMenu(@PathVariable Long roleId,@PathVariable Long[] menuIds){
        /**
         * 因为我们用的路径参数，前端可能传过来的menuIds是一个空的，但是为空的话无法匹配上面的路径
         * 所以如果为空，我们让前端传一个-1过来，如果是-1说明当前角色一个权限也没有选择
         */
        if(menuIds.length==1&&menuIds[0].equals(-1L)){
            menuIds=new Long[]{};
        }
        this.roleService.saveRoleMenu(roleId, menuIds);
        return AjaxResult.success();
    }

}