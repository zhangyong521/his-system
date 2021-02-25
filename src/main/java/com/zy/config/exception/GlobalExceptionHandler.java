package com.zy.config.exception;

import com.zy.common.vo.AjaxResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zy
 * @version 1.0
 * @date 2021/2/25 17:44
 * @description TODO
 */
@RestControllerAdvice //如果出现异常之后返回json对象
//@ControllerAdvice //如果出现异常之后进行页面跳转
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public AjaxResult methodArgumentNotValidException(MethodArgumentNotValidException e){
        //{code:400,msg:'',
        // data:[{objectName:'loginBodyDto','filed':'username','defalultMessage':''},
        // {objectName:'loginBodyDto','filed':'username','defaultMessage':''}]}

        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<Map<String,Object>> data=new ArrayList<>();
        return getAjaxResult(bindingResult, allErrors, data);
    }

    @ExceptionHandler(value = {BindException.class})
    public AjaxResult bindExceptionHandler(BindException e){
        List<Map<String,Object>> data=new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        return getAjaxResult(bindingResult, allErrors, data);
    }

    private AjaxResult getAjaxResult(BindingResult bindingResult, List<ObjectError> allErrors, List<Map<String, Object>> data) {
        for (ObjectError error : allErrors) {
            FieldError fieldError= (FieldError) error;
            Map<String,Object> map=new HashMap<>();
            map.put("objectName",bindingResult.getObjectName());
            map.put("filed",fieldError.getField());
            map.put("defaultMessage",fieldError.getDefaultMessage());
            data.add(map);
        }
        return AjaxResult.fail("后端数据校验异常", data);
    }

}