package com.hunsy.commons.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回数据
 * 
 * @author hunsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PgRet<T> {

	private Long totalNum;
	private Integer totalPage;
	private Integer currentPage;
	private List<T> dataResult = new ArrayList<T>();

	/**
	 * 
	 * @param pageNo 当前页码
	 * @param totalNum 总记录
	 * @param totalPages 总页码
	 * @param data 数据
	 */
	public PgRet(Integer pageNo,Long totalNum,Integer totalPages,List<T> data) {
		this.currentPage = pageNo;
		this.totalNum = totalNum;
		this.totalPage = totalPages;
		if (data != null && !data.isEmpty()) {
			this.dataResult.addAll(data);
		}
	}

}
