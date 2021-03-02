package com.zy.project.system.controller;

import com.zy.common.aspectj.annotation.Log;
import com.zy.common.aspectj.enums.BusinessType;
import com.zy.common.vo.AjaxResult;
import com.zy.common.vo.DataGridView;
import com.zy.project.system.dto.OperLogDto;
import com.zy.project.system.service.OperLogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/2 11:11
 * @description 操作日志的控制层
 */
@Log4j2
@RestController
@RequestMapping("system/operLog")
public class OperLogController {

    @Autowired
    private OperLogService operLogService;

    /**
     * 分页查询
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(OperLogDto operLogDto) {
        DataGridView gridView = operLogService.listForPage(operLogDto);
        return AjaxResult.success("查询成功", gridView.getData(), gridView.getTotal());
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteOperLogByIds/{infoIds}")
    @Log(title = "操作日志管理-删除", businessType = BusinessType.DELETE)
    public AjaxResult deleteOperLogByIds(@PathVariable Long[] infoIds) {
        return AjaxResult.toAjax(this.operLogService.deleteOperLogByIds(infoIds));
    }

    /**
     * 清空删除
     */
    @DeleteMapping("clearAllOperLog")
    @Log(title = "操作日志管理-清空", businessType = BusinessType.DELETE)
    public AjaxResult clearAllOperLog() {
        return AjaxResult.toAjax(this.operLogService.clearAllOperLog());
    }

}
