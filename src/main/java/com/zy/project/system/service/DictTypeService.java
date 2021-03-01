package com.zy.project.system.service;

import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.DictType;
import com.zy.project.system.dto.DictTypeDto;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 14:13
 * @description 字典类型的逻辑接口
 */
public interface DictTypeService {

    /**
     * 分页查询字典类型
     * @param dictTypeDto
     * @return
     */
    DataGridView listPage(DictTypeDto dictTypeDto);

    /**
     * 查询所有字典类型
     * @return
     */
    DataGridView list();

    /**
     * 检查字典类型是否存在
     * @param dictId
     * @param dictType
     * @return
     */
    Boolean checkDictTypeUnique(Long dictId,String dictType);

    /**
     * 插入新的字典类型
     * @param dictTypeDto
     * @return
     */
    int insert(DictTypeDto dictTypeDto);
    /**
     * 修改的字典类型
     * @param dictTypeDto
     * @return
     */
    int update(DictTypeDto dictTypeDto);

    /**
     * 根据ID删除字典类型
     * @param dictIds
     * @return
     */
    int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 根据ID查询一个字典类型
     * @param dictId
     * @return
     */
    DictType selectDictTypeById(Long dictId);

}
