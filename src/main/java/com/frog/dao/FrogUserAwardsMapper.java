package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogAwards;

public interface FrogUserAwardsMapper extends BaseMapper<FrogAwards> {
	
	List<FrogAwards> selectUserAllAwards(@Param("user_id")Integer user_id,@Param("page")Integer page);
	
	List<FrogAwards> selectUserAwardsByDate(@Param("add_time")String add_time);
	
	List<FrogAwards> selectUserAllAwardsNoLimit(@Param("user_id")Integer user_id);
}
