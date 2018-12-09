package com.hunsy.commons.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.hunsy.commons.ctx.CtxHolder;

/**
 * 请求的工具类
 * 
 * @author admin
 *
 */
public class ReqUtils {

	/**
	 * 将请求的所有信息写入ctx中
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public static void parseToCtx(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", request.getRequestURL().toString());
		map.put("path", request.getRequestURI());
		map.put("method", request.getMethod());
		map.put("ip", request.getRemoteAddr());
		// 获取参数
		Map<String, Object> params = new HashMap<String, Object>(5);
		Map<String, String[]> ms = request.getParameterMap();
		if (ms != null && !ms.isEmpty()) {
			params.putAll(ms);
		}
		// 获取body中的参数
		String jsonStr = "";
		try {
			jsonStr = IOUtils.toString(request.getInputStream(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!"".equals(jsonStr) && null != jsonStr) {
			Map<String, Object> m = JSON.parseObject(jsonStr, Map.class);
			params.putAll(m);
		}
		map.put("args", params);
		Map<String, String> headMap = new HashMap<String, String>(5);
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String name = headers.nextElement();
			String val = request.getHeader(name);
			headMap.put(name, val);
		}
		map.put("headers", headMap);
		CtxHolder.setReqCtx(map);
	}
}
