package com.zy.project.system.controller;

import com.zy.common.aspectj.annotation.Log;
import com.zy.common.aspectj.enums.BusinessType;
import com.zy.common.utils.ShiroSecurityUtils;
import com.zy.common.vo.AjaxResult;
import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.DictData;
import com.zy.project.system.dto.DictDataDto;
import com.zy.project.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 16:01
 * @description 数据字典的控制层
 */
@RestController
@RequestMapping("system/dict/data")
public class DictDataController {

    @Autowired
    private DictDataService dictDataService;

    /**
     * 根据字典类型查询字典数据
     */
    @GetMapping("getDataByType/{dictType}")
    public AjaxResult getDataByType(@PathVariable("dictType") String dictType) {
        List<DictData> dictDataList = this.dictDataService.selectDictDataByDictType(dictType);
        return AjaxResult.success(dictDataList);
    }

    /**
     * 分页查询
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(DictDataDto dictDataDto) {
        DataGridView view = this.dictDataService.listPage(dictDataDto);
        return AjaxResult.success("查询成功", view.getData(), view.getTotal());
    }

    /**
     * 添加一个字典类型
     */
    @PostMapping("addDictData")
    @Log(title = "字典数据管理-添加", businessType = BusinessType.INSERT)
    public AjaxResult addDictData(@Validated DictDataDto dictDataDto) {
        dictDataDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        this.dictDataService.addDictData(dictDataDto);
        return AjaxResult.success();
    }

    /**
     * 根据ID查询一个字典数据
     */
    @GetMapping("getOne/{dictId}")
    public AjaxResult getDictData(@PathVariable @Validated Long dictId) {
        return AjaxResult.success(this.dictDataService.selectDictDataById(dictId));
    }

    /**
     * 修改一个字典数据
     */
    @PutMapping("updateDictData")
    @Log(title = "字典数据管理-修改", businessType = BusinessType.UPDATE)
    public AjaxResult updateDictData(@Validated DictDataDto dictDataDto) {
        dictDataDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        this.dictDataService.updateDictData(dictDataDto);
        return AjaxResult.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteDictDataByIds/{dictCodes}")
    @Log(title = "字典数据管理-删除", businessType = BusinessType.DELETE)
    public AjaxResult updateDictData(@PathVariable @Validated Long[] dictCodes) {
        return AjaxResult.toAjax(this.dictDataService.deleteDictDataByIds(dictCodes));
    }

}
