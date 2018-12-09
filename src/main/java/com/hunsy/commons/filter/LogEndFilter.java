package com.hunsy.commons.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.hunsy.commons.adaptor.LogAdaptor;
import com.hunsy.commons.ctx.CtxHolder;

/**
 * 日志结束拦截器
 * @author admin
 *
 */
public class LogEndFilter implements Filter{

	@Autowired
	private LogAdaptor logAdaptor;
	
	@Override
	public void init(FilterConfig filterConfig) {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		Integer status = resp.getStatus();
		Map<String, Object> reqCtx = CtxHolder.getReqCtx();
		reqCtx.put("responseStatus", status);
		logAdaptor.setLogInfo(reqCtx);
		logAdaptor.doLog();
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		
	}
}
