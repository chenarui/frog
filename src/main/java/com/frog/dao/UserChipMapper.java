package com.frog.dao;

import org.apache.ibatis.annotations.Param;

import com.frog.model.UserChip;

public interface UserChipMapper {
	
	public UserChip selectUserChipByUser(@Param("user_id")Integer user_id);
	
	public Integer insert(UserChip userChip);
	
	public Integer updateChip(UserChip userChip);
	
	
}
