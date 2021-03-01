package com.zy.common.aspectj.enums;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 16:01
 * @description 用户操作类型枚举
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER("0"),

    /**
     * 新增
     */
    INSERT("1"),

    /**
     * 修改
     */
    UPDATE("2"),

    /**
     * 删除
     */
    DELETE("3"),

    /**
     * 授权
     */
    GRANT("4"),

    /**
     * 导出
     */
    EXPORT("5"),

    /**
     * 导入
     */
    IMPORT("6"),

    /**
     * 强退
     */
    FORCE("7"),

    /**
     * 清空数据
     */
    CLEAN("8");

    private String value;

    BusinessType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
