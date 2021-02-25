package com.zy.config.shiro;

import com.zy.project.system.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 16:58
 * @description 权限角色实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser implements Serializable {

    private User user;
    /**
     * 角色
     */
    private List<String> roles = Collections.EMPTY_LIST;
    /**
     * 权限
     */
    private List<String> permissions = Collections.EMPTY_LIST;
}