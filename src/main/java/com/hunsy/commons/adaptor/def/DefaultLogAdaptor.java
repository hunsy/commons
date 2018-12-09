package com.hunsy.commons.adaptor.def;

import java.util.Map;

import com.hunsy.commons.adaptor.LogAdaptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuxindime
 */
@Component
@Slf4j
public class DefaultLogAdaptor extends LogAdaptor {

	public DefaultLogAdaptor(Map<String, Object> logInfo) {
		super(logInfo);
	}

	@Override
	public void mqLog() {
	}

}
