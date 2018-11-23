package com.frog.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BaseMapper<T> {

	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
	
	//Integer page,Integer pageSize
	List<T> list(Map<String, Object> params);
	
	long count(Map<String, Object> params);
}
