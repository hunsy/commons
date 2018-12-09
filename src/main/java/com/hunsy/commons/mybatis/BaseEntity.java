package com.hunsy.commons.mybatis;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Entity基础类
 *
 * @author hunsy
 */
@Data
public abstract class BaseEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3374571936673552523L;
	@Id
    private Long id;
}
