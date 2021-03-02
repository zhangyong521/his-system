package com.zy.project.system.service;

import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.OperLog;
import com.zy.project.system.dto.OperLogDto;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/2 10:27
 * @description 日志文件的逻辑接口层
 */
public interface OperLogService {
    /**
     * 插入操作日志
     *
     * @param operLog
     */
    void insertOperLog(OperLog operLog);

    /**
     * 分页查询操作日志
     *
     * @param operLogDto
     * @return
     */
    DataGridView listForPage(OperLogDto operLogDto);

    /**
     * 根据ID删除操作日志
     *
     * @param infoIds
     * @return
     */
    int deleteOperLogByIds(Long[] infoIds);

    /**
     * 清空操作日志
     *
     * @return
     */
    int clearAllOperLog();

}
