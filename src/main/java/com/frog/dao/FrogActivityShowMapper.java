package com.frog.dao;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogActivityShow;

public interface FrogActivityShowMapper {
	FrogActivityShow selectByUseridAndActivityId(@Param("user_id")Integer user_id,@Param("activityid")Integer activityid);
	void insert(FrogActivityShow show);
}
