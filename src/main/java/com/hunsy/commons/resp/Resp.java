package com.hunsy.commons.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应bean
 *
 * @author hunsy
 */
@Data
@AllArgsConstructor
public class Resp implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6466684027668649785L;
	private Integer code;
    private String msg;
    private Object data;
}
