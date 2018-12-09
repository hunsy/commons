package com.hunsy.commons.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import com.hunsy.commons.cfg.CommonsCfg;

/**
 * jwt工具类
 *
 * @author hunsy
 */
public class JwtUtils {


    /**
     * 生产Token
     *
     * @param userStr
     * @return
     */
    public static String createToken(String userStr) {
    	CommonsCfg cfg = ApplicationContextUtils.getBean(CommonsCfg.class);
        String token = Jwts.builder()
                .setSubject(userStr)
                .setExpiration(new Date(System.currentTimeMillis() + cfg.getJwtExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, cfg.getJwtSigningKey())
                .compact();
        return token;
    }

    /**
     * 解析Token
     *
     * @param token Token字符串
     * @return
     */
    public static String parseToken(String token) {
    	CommonsCfg cfg = ApplicationContextUtils.getBean(CommonsCfg.class);
        String userStr = Jwts.parser()
                .setSigningKey(cfg.getJwtSigningKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userStr;
    }

}
