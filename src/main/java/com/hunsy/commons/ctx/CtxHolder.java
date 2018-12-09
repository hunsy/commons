package com.hunsy.commons.ctx;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求的上下文环境
 * 
 * @author hunsy
 */
public class CtxHolder {

	
	/**
	 * 请求的基本信息。 path method URL IP arguments headrs
	 */
	private static ThreadLocal<Map<String, Object>> reqCtx = new ThreadLocal<Map<String, Object>>();
	/**
	 * 登录的用户信息
	 */
	private static ThreadLocal<User> userCtx = new ThreadLocal<User>();

	/**
	 * 获取上下文环境
	 * 
	 * @return
	 */
	public static Map<String, Object> getReqCtx() {
		Map<String, Object> ctx = reqCtx.get();
		if (ctx == null) {
			ctx = new HashMap<String, Object>(5);
		}
		return ctx;
	}

	/**
	 * 设置上下文环境
	 * 
	 * @param req
	 */
	public static void setReqCtx(Map<String, Object> req) {
		Map<String, Object> ctx = getReqCtx();
		ctx.putAll(req);
	}

	/**
	 * 获取登录用户
	 * 
	 * @return
	 */
	public static User setUserCtx() {

		return userCtx.get();
	}

	/**
	 * 设置登录用户
	 * 
	 * @param user
	 */
	public static void setUserCtx(User user) {
		userCtx.set(user);
	}

}
