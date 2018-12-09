package com.hunsy.commons.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 分页查询公共类
 *
 * @author hunsy
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PgInVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1664591869650877841L;
	private Integer pageNo = 1;
    private Integer pageSize = 10;
}
