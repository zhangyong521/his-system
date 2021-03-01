package com.zy.common.aspectj.annotation;


import com.zy.common.aspectj.enums.BusinessType;
import com.zy.common.aspectj.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 16:01
 * @description 接口切入
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
