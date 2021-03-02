package com.zy.project.system.service;


import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.LoginInfo;
import com.zy.project.system.dto.LoginInfoDto;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/2 14:26
 * @description 登录日志的逻辑接口层
 */
public interface LoginInfoService {
    /**
     * 添加
     *
     * @param loginInfo
     * @return
     */
    int insertLoginInfo(LoginInfo loginInfo);

    /**
     * 分页查询
     *
     * @param loginInfoDto
     * @return
     */
    DataGridView listForPage(LoginInfoDto loginInfoDto);

    /**
     * 删除登陆日志
     *
     * @param infoIds
     * @return
     */
    int deleteLoginInfoByIds(Long[] infoIds);

    /**
     * 清空登陆日志
     *
     * @return
     */
    int clearLoginInfo();

}
