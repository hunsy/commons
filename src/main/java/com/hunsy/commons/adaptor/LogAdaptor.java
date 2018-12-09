package com.hunsy.commons.adaptor;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志处理的adapter
 * @author admin
 *
 */
@Slf4j
@Data
@AllArgsConstructor
public abstract class LogAdaptor {

	private Map<String,Object> logInfo;
	
	public void doLog(){
		this.logLog();
		this.mqLog();
	}
	
	/**
	 * 向日志系统输出日志
	 */
	public void logLog(){
		log.info("{}",JSON.toJSONString(logInfo));
	}
	
	/**
	 * 向mq输出日志
	 */
	public abstract void mqLog();
	
}
