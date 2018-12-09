package com.hunsy.commons.mybatis.service;


import tk.mybatis.mapper.entity.Example;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hunsy.commons.mybatis.BaseEntity;

/**
 * @author hunsy
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 主键查询
     *
     * @param id 实例主键
     * @return
     */
    T selectById(Long id);

    /**
     * 查询所有实例
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 条件查询
     *
     * @param t
     * @return
     */
    List<T> selectByWhere(T t);

    /**
     * 条件查询，获取一个实例
     *
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 条件查询
     *
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 根据条件分页查询
     *
     * @param t        T
     * @param page     当前页数
     * @param pageSize 每页显示条数
     * @return PageInfo
     */
    PageInfo<T> selectByWhere(T t, int page, int pageSize);

    /**
     * 根据条件分页查询
     *
     * @param t        条件
     * @param page     页码
     * @param pageSize 每页数据
     * @return
     */
    PageInfo<T> selectByWhere(Example t, int page, int pageSize);

    /**
     * 分页查询
     *
     * @param page     当前页数
     * @param pageSize 每页显示条数
     * @return PageInfo
     */
    PageInfo<T> selectByPage(int page, int pageSize);

    /**
     * 新增数据
     *
     * @param t
     * @return
     */
    Integer insert(T t);

    /**
     * 按条件新增（空字段不插入）
     *
     * @param t
     * @return
     * @throws
     */
    Integer insertSelective(T t);

    /**
     * 全字段更新
     *
     * @param t
     * @return
     */
    Integer updated(T t);

    /**
     * 条件更新，空值字段不做判断
     *
     * @param t
     * @return
     */
    Integer updatedSelective(T t);

    /**
     * 条件更新
     *
     * @param t
     * @param example
     * @return
     */
    Integer updatedByExampleSelective(T t, Example example);

    /**
     * 主键删除
     *
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 删除多个id对应得数据
     *
     * @param ids
     * @param property
     * @param clazz
     * @return
     */
    Integer deleteByIds(List<Object> ids, String property, Class<T> clazz);

    /**
     * 删除
     *
     * @param t
     * @return
     */
    Integer delete(T t);

    /**
     * 条件删除
     *
     * @param example
     * @return
     */
    Integer deleteByExample(Example example);

    /**
     * 统计数量
     *
     * @param t
     * @return
     */
    int count(T t);
}
