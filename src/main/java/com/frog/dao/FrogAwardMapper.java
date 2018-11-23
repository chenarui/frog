package com.frog.dao;

import java.util.List;

import com.frog.model.FrogAward;

public interface FrogAwardMapper {
	public List<FrogAward> selectFrogAwardByUser();
	
	public FrogAward selectByAwardId(Integer award_id);
	
	public List<FrogAward> selectFrogAwardByType(Integer type);
}
