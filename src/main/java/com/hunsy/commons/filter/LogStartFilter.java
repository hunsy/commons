package com.hunsy.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.hunsy.commons.utils.ReqUtils;
/**
 * 开始拦截器。用于获取请求参数等信息
 * @author admin
 *
 */
public class LogStartFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;		
		ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
		ReqUtils.parseToCtx(request);
		filterChain.doFilter(requestWrapper, servletResponse);
	}

	@Override
	public void destroy() {
		
	}
}
