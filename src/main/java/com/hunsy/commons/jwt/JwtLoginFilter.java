package com.hunsy.commons.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hunsy.commons.cfg.CommonsCfg;
import com.hunsy.commons.resp.Resp;
import com.hunsy.commons.utils.JwtUtils;

/**
 * jwt的登录拦截
 * 
 * @author admin
 *
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	@Autowired
	private CommonsCfg cfg;

	public JwtLoginFilter(AuthenticationManager authenticationManager) {

		// 设置拦截的登录地址
		if (cfg.getLoginActionUrl() != null) {
			setFilterProcessesUrl(cfg.getLoginActionUrl());
		}
		this.setAuthenticationManager(authenticationManager);
	}

	/**
	 * 进行授权
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		try {
			if (request.getHeader("content-Type").contains("application/json")) {
				String str = IOUtils
						.toString(request.getInputStream(), "UTF-8");
				JSONObject obj = JSON.parseObject(str);
				super.setUsernameParameter(str);
				super.setPasswordParameter(obj.getString("password") == null ? "wechatjscode"
						: obj.getString("password"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException {
		String userStr = ((org.springframework.security.core.userdetails.User) authResult
				.getPrincipal()).getUsername();

		String token = JwtUtils.createToken(userStr);
		Map<String, Object> tokenMap = new HashMap<String, Object>(1);
		tokenMap.put("Token", token);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(200);
		response.getWriter().write(
				JSON.toJSONString(new Resp(200, "登录成功", tokenMap)));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException {

		response.setContentType("application/json;charset=utf-8");
		response.setStatus(200);
		response.getWriter().write(
				JSON.toJSONString(new Resp(403, "授权失败", null)));
	}
}
