package com.zy.project.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.constants.Constants;
import com.zy.common.vo.DataGridView;
import com.zy.project.system.domain.DictData;
import com.zy.project.system.dto.DictDataDto;
import com.zy.project.system.mapper.DictDataMapper;
import com.zy.project.system.service.DictDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date 2021/3/1 15:42
 * @description 业务逻辑层
 */
@Service
public class DictDataServiceImpl implements DictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 之前是从数据库里面查询
     * 因为我们做到redis的缓存，所以现在要去redis里面去查询
     * @param dictType
     * @return
     */
    @Override
    public List<DictData> selectDictDataByDictType(String dictType) {
        if (StringUtils.isBlank(dictType)) {
            return Collections.EMPTY_LIST;
        }
        //先从Redis里面查询
        String key = Constants.DICT_REDIS_PREFIX + dictType;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String json = operations.get(key);
        //说明redis里面没有
        if (StringUtils.isBlank(json)) {
            QueryWrapper<DictData> qw = new QueryWrapper<>();
            qw.eq(DictData.COL_DICT_TYPE, dictType);
            //必须有效
            qw.eq(DictData.COL_STATUS, Constants.STATUS_TRUE);
            List<DictData> dictDataList = this.dictDataMapper.selectList(qw);
            //放入redis
            operations.set(key, JSON.toJSONString(dictDataList));
            return dictDataList;
        } else {//说明redis里面有
            return JSON.parseArray(json, DictData.class);
        }
    }

    @Override
    public DataGridView listPage(DictDataDto dictDataDto) {
        Page<DictData> page = new Page<>(dictDataDto.getPageNum(), dictDataDto.getPageSize());
        QueryWrapper<DictData> qw = new QueryWrapper<>();
        qw.eq(StringUtils.isNotBlank(dictDataDto.getDictType()), DictData.COL_DICT_TYPE, dictDataDto.getDictType());
        qw.eq(StringUtils.isNotBlank(dictDataDto.getStatus()), DictData.COL_STATUS, dictDataDto.getStatus());
        qw.like(StringUtils.isNotBlank(dictDataDto.getDictLabel()), DictData.COL_DICT_LABEL, dictDataDto.getDictLabel());
        qw.orderByAsc(DictData.COL_DICT_SORT);
        this.dictDataMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public void addDictData(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtil.copyProperties(dictDataDto, dictData);
        //设置创建人和创建时间
        dictData.setCreateBy(dictDataDto.getSimpleUser().getUserName());
        dictData.setCreateTime(DateUtil.date());
        this.dictDataMapper.insert(dictData);
    }

    @Override
    public DictData selectDictDataById(Long dictId) {
        return this.dictDataMapper.selectById(dictId);
    }

    @Override
    public void updateDictData(DictDataDto dictDataDto) {
        DictData dictData = new DictData();
        BeanUtil.copyProperties(dictDataDto, dictData);
        //设置修改人和修改时间
        dictData.setUpdateBy(dictDataDto.getSimpleUser().getUserName());
        dictData.setUpdateTime(DateUtil.date());
        this.dictDataMapper.updateById(dictData);
    }

    @Override
    public int deleteDictDataByIds(Long[] dictCodes) {
        List<Long> ids = Arrays.asList(dictCodes);
        if (ids != null && ids.size() > 0) {
            return this.dictDataMapper.deleteBatchIds(ids);
        }
        return 0;
    }

}
