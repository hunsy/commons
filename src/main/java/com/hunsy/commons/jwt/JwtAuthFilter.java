package com.hunsy.commons.jwt;

import com.alibaba.fastjson.JSON;
import com.hunsy.commons.ctx.CtxHolder;
import com.hunsy.commons.ctx.User;
import com.hunsy.commons.resp.Resp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权
 *
 * @author hunsy
 */
public class JwtAuthFilter extends BasicAuthenticationFilter {


    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {

        String userStr = ((org.springframework.security.core.userdetails.User) authResult
                .getPrincipal()).getUsername();
        User user = JSON.parseObject(userStr, User.class);
        CtxHolder.setUserCtx(user);
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        response.getWriter().write(
                JSON.toJSONString(new Resp(401, "鉴权失败", null)));
    }
}
