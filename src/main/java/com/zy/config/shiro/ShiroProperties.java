package com.zy.config.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 17:01
 * @description 获取shiro的配置信息
 */
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroProperties {
    /**
     * 密码加密方式
     */
    private String hashAlgorithmName = "md5";
    /**
     * 密码散列次数
     */
    private Integer hashIterations = 2;

    /**
     * 不拦击的路径
     */
    private String[] anonUrls;

    /**
     * 拦截的路径
     */
    private String[] authcUrls;
}
