package com.hunsy.commons.mybatis;

import tk.mybatis.mapper.common.Mapper;

/**
 * 基础的Mapper
 *
 * @author hunsy
 */
public interface BaseMapper<T extends BaseEntity> extends Mapper<T> {
}
