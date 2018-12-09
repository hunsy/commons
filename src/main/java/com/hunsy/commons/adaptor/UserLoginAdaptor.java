package com.hunsy.commons.adaptor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户登录的适配器
 * @author admin
 *
 */
public abstract class UserLoginAdaptor implements UserDetailsService {

		
	/**
	 * type  1 普通密码登录 2 手机验证码登录 3 微信code登录
	 * @param loginInfo 登录信息
	 */
	@Override
	public UserDetails loadUserByUsername(String loginInfo)
			throws UsernameNotFoundException {
		JSONObject obj = JSON.parseObject(loginInfo);
		Integer type = obj.getInteger("type");
		User user = null;
		switch (type) {
			case 1:
			user = findByUsername(obj.getString("username"));
			break;

			default:
				break;
		}
		return user;
	}

	/**
	 * 用户密码登录
	 * @param username
	 * @return
	 */
	protected abstract User findByUsername(String username);
	
	/**
	 * 手机验证码登录
	 * @return
	 */
	protected abstract User findByMobile(String mobile,String verifyCode);

}
