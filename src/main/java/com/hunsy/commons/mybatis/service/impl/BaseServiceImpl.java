package com.hunsy.commons.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hunsy.commons.mybatis.BaseEntity;
import com.hunsy.commons.mybatis.service.BaseService;
import com.hunsy.commons.utils.GeneratorID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author hunsy
 */
@Component
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    /**
     * 根据主键id查询
     *
     * @param id
     * @return
     */
    @Override
    public T selectById(Long id) {
        return this.mapper.selectByPrimaryKey(id);

    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return this.mapper.selectAll();
    }

    /**
     * 根据条件查询数据集合
     *
     * @param t
     * @return
     */
    @Override
    public List<T> selectByWhere(T t) {
        return this.mapper.select(t);
    }

    /**
     * 根据条件查询一条数据
     *
     * @param t
     * @return
     */
    @Override
    public T selectOne(T t) {
        return this.mapper.selectOne(t);
    }

    /**
     * 自定义example查询数据
     *
     * @param example
     * @return
     */
    @Override
    public List<T> selectByExample(Example example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public PageInfo<T> selectByWhere(T t, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<T> list = this.selectByWhere(t);
        return new PageInfo<T>(list);
    }

    @Override
    public PageInfo<T> selectByWhere(Example t, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<T> list = this.mapper.selectByExample(t);
        return new PageInfo<T>(list);
    }

    @Override
    public PageInfo<T> selectByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        List<T> list = this.selectAll();
        return new PageInfo<T>(list);
    }

    /**
     * 新增数据
     *
     * @param t
     * @return 数据条数
     */
    @Override
    public Integer insert(T t) {

        if (t.getId() == null) {
            t.setId(GeneratorID.getId());
        }
        return this.mapper.insert(t);
    }

    /**
     * 新增数据,null的属性不会保存,保存对象时某个属性不赋值,即不保存数据库该字段,
     *
     * @param t
     * @return
     */
    @Override
    public Integer insertSelective(T t) {

        if (t.getId() == null) {
            t.setId(GeneratorID.getId());
        }
        return this.mapper.insertSelective(t);
    }

    /**
     * 根据主键id更新数据
     *
     * @param t
     * @return
     */
    @Override
    public Integer updated(T t) {
        return this.mapper.updateByPrimaryKey(t);

    }

    /**
     * 主键更新数据, null的属性不会保存 ,更新对象时某个属性不赋值,即不更改数据库该字段,
     *
     * @param t
     * @return
     */
    @Override
    public Integer updatedSelective(T t) {
        return this.mapper.updateByPrimaryKeySelective(t);

    }

    /**
     * 自定义example 更新数据
     *
     * @param t
     * @param example
     * @return
     */
    @Override
    public Integer updatedByExampleSelective(T t, Example example) {
        return this.mapper.updateByExampleSelective(t, example);
    }

    /**
     * 根据主键id删除数据
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @param property
     * @param clazz
     * @return
     */
    @Override
    public Integer deleteByIds(List<Object> ids, String property, Class<T> clazz) {
        // 构造一个example
        Example example = new Example(clazz);
        // 创建一个条件
        example.createCriteria().andIn(property, ids);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据entity删除
     *
     * @param t
     * @return
     */
    @Override
    public Integer delete(T t) {
        return this.mapper.delete(t);
    }

    /**
     * 根据条件删除entity
     *
     * @param example 条件
     * @return 返回删除记录的条数
     */
    @Override
    public Integer deleteByExample(Example example) {

        return this.mapper.deleteByExample(example);
    }

    /**
     * 统计数量
     */
    @Override
    public int count(T t) {
        return mapper.selectCount(t);
    }
}
