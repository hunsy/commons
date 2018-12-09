package com.hunsy.commons.cfg;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuxindime
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix="commons")
@Component
public class CommonsCfg {
	/**
	 * 登录的url
	 */
	private String loginActionUrl;
	/**
	 * jwt签名key
	 */
	private String jwtSigningKey = "hunsy`s key";
	/**
	 * jwt的Token有效期限，默认1天
	 */
	private Integer jwtExpirationTime = 60 * 60 * 24 * 1000;
	
}
