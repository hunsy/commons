package com.hunsy.commons.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hunsy.commons.ex.BizEx;
import com.hunsy.commons.resp.Resp;

/**
 * 异常统一处理
 * @author admin
 *
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExHandler {
	
	
	   /**
     * 处理业务层错误信息
     *
     * @param biz
     */
    @ExceptionHandler(BizEx.class)
    public Object bizExceptionHandler(BizEx biz) {
    	
    	Integer code = biz.getCode();
    	String msg = biz.getMsg();
    	log.warn("请求业务异常:code:{},msg:{}",code,msg);
    	return new Resp(code, msg, null);
    }
    
    

	
	
	
}
