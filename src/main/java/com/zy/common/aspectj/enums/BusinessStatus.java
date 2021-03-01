package com.zy.common.aspectj.enums;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 16:01
 * @description 状态枚举
 */
public enum BusinessStatus {
    /**
     * 成功
     */
    SUCCESS("0"),

    /**
     * 失败
     */
    FAIL("1");

    private String value;

    BusinessStatus(String status) {
        this.value = status;
    }

    public String getValue() {
        return value;
    }
}
