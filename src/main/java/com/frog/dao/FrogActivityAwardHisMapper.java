package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogActivityAwardHis;

public interface FrogActivityAwardHisMapper {
	int insertFrogActivityAwardHis(FrogActivityAwardHis his);
	List<FrogActivityAwardHis> selectAllAwardsByUserId(@Param("user_id")Integer user_id,@Param("state")String state);
	void updateFrogActivityAwardHis(FrogActivityAwardHis his);
}
