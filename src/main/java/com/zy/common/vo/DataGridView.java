package com.zy.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 14:25
 * @description 表格的数据传输对象
 */
@ApiModel(value="com-bjsxt-vo-DataGridView")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView implements Serializable {
    private Long total;
    private List<?> data;
}