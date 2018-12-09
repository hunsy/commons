package com.hunsy.commons.adaptor.def;

import com.hunsy.commons.adaptor.UserLoginAdaptor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


/**
 * @author liuxindime
 */
@Component
public class DefaultUserLoginAdaptor extends UserLoginAdaptor {
    @Override
    protected User findByUsername(String username) {
        return null;
    }

    @Override
    protected User findByMobile(String mobile, String verifyCode) {
        return null;
    }
}
