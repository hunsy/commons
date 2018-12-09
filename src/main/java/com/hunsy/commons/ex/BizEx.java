package com.hunsy.commons.ex;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 *
 * @author hunsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizEx extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 207985177502634102L;
	private Integer code;
	private String msg;
		
	public BizEx(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
