package com.zy.project.system.controller;

import com.zy.common.aspectj.annotation.Log;
import com.zy.common.aspectj.enums.BusinessType;
import com.zy.common.utils.ShiroSecurityUtils;
import com.zy.common.vo.AjaxResult;
import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.Dept;
import com.zy.project.system.dto.DeptDto;
import com.zy.project.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/2 16:01
 * @description 科室的控制层
 */
@RestController
@RequestMapping("system/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 分页查询
     */
    @GetMapping("listDeptForPage")
    public AjaxResult listDeptForPage(DeptDto deptDto) {
        DataGridView gridView = this.deptService.listPage(deptDto);
        return AjaxResult.success("查询成功", gridView.getData(), gridView.getTotal());
    }

    /**
     * 不分页面查询有效的
     */
    @GetMapping("selectAllDept")
    public AjaxResult selectAllDept() {
        List<Dept> lists = this.deptService.list();
        return AjaxResult.success(lists);
    }

    /**
     * 查询一个
     */
    @GetMapping("getDeptById/{deptId}")
    public AjaxResult getDeptById(@PathVariable @Validated @NotEmpty(message = "科室ID为空") Long deptId) {
        Dept dept = this.deptService.getOne(deptId);
        return AjaxResult.success(dept);
    }

    /**
     * 添加
     */
    @PostMapping("addDept")
    @Log(title = "科室管理-添加", businessType = BusinessType.INSERT)
    public AjaxResult addDept(@Validated DeptDto deptDto) {
        deptDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.deptService.addDept(deptDto));
    }

    /**
     * 修改
     */
    @Log(title = "科室管理-修改", businessType = BusinessType.UPDATE)
    @PutMapping("updateDept")
    public AjaxResult updateDept(@Validated DeptDto deptDto) {
        deptDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.deptService.updateDept(deptDto));
    }

    /**
     * 删除
     */
    @Log(title = "科室管理-删除", businessType = BusinessType.DELETE)
    @DeleteMapping("deleteDeptByIds/{deptIds}")
    public AjaxResult delete(@PathVariable @Validated @NotEmpty(message = "科室ID为空") Long[] deptIds) {
        return AjaxResult.toAjax(this.deptService.deleteDeptByIds(deptIds));
    }

}