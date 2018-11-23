package com.frog.serviceImpl;

import java.util.List;
import java.util.Map;

import com.frog.dao.BaseMapper;
import com.frog.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	public abstract BaseMapper<T> getMapper();
	
	public List<T> list(Integer page,Integer pageSize,Map<String, Object> params) {
		if (page != null && pageSize != null) {
			params.put("startPage", (page - 1) * pageSize);
			params.put("rows", pageSize);
		}
		
		return this.getMapper().list(params);
	}
	
	//分页时合计查询
	public long count(Map<String, Object> params) {
		return this.getMapper().count(params);
	}

	public T viewById(int id) {
		return this.getMapper().selectByPrimaryKey(id);
	}

	public boolean delete(int id) {
		return this.getMapper().deleteByPrimaryKey(id) > 0;
	}

	public T save(T t) {
		System.out.println("t.values=="+t.toString());
		int row = this.getMapper().insertSelective(t);
		if (row > 0)
			return t;
		return null;
	}

	public boolean update(T t) {
		return this.getMapper().updateByPrimaryKeySelective(t) > 0;
	}
	

	
	
}
