package com.zy.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 15:28
 * @description 登陆的数据输出对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBodyDto {
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**验证码*/
    private String code;

}
