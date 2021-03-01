package com.zy.common.aspectj.enums;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 16:01
 * @description 用户类别枚举
 */
public enum OperatorType {
    /**
     * 其它
     */
    OTHER("0"),

    /**
     * 后台用户
     */
    MANAGE("1"),

    /**
     * 手机端用户
     */
    MOBILE("2");

    private String value;

    OperatorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

