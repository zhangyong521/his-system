package com.zy.project.system.service;

import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.DictData;
import com.zy.project.system.dto.DictDataDto;

import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 15:42
 * @description 业务接口层
 */
public interface DictDataService {


    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return
     */
    List<DictData> selectDictDataByDictType(String dictType);

    /**
     * 分页查询字典数据
     *
     * @param dictDataDto
     * @return
     */
    DataGridView listPage(DictDataDto dictDataDto);

    /**
     * 添加一个字典数据
     *
     * @param dictDataDto
     */
    void addDictData(DictDataDto dictDataDto);

    /**
     * 根据字典数据ID查询一个字典数据信息
     *
     * @param dictId
     * @return
     */
    DictData selectDictDataById(Long dictId);

    /**
     * 更新的字典数据
     *
     * @param dictDataDto
     */
    void updateDictData(DictDataDto dictDataDto);

    /**
     * 删除字典数据
     *
     * @param dictCodes
     * @return
     */
    int deleteDictDataByIds(Long[] dictCodes);

}
