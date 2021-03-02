package com.zy.project.system.controller;

import com.zy.common.vo.AjaxResult;
import com.zy.common.vo.DataGridView;
import com.zy.project.system.dto.LoginInfoDto;
import com.zy.project.system.service.LoginInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/2 14:40
 * @description 登录日志的控制层
 */
@Log4j2
@RestController
@RequestMapping("system/loginInfo")
public class LoginInfoController {

    @Autowired
    private LoginInfoService loginInfoService;

    /**
     * 分页查询
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(LoginInfoDto loginInfoDto) {
        DataGridView gridView = loginInfoService.listForPage(loginInfoDto);
        return AjaxResult.success("查询成功", gridView.getData(), gridView.getTotal());
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteLoginInfoByIds/{infoIds}")
    public AjaxResult deleteLoginInfoByIds(@PathVariable Long[] infoIds) {
        return AjaxResult.toAjax(this.loginInfoService.deleteLoginInfoByIds(infoIds));
    }

    /**
     * 清空删除
     */
    @DeleteMapping("clearLoginInfo")
    public AjaxResult clearLoginInfo() {
        return AjaxResult.toAjax(this.loginInfoService.clearLoginInfo());
    }

}
