package com.frog.dao;

import com.frog.model.FrogAwardsDetail;

public interface FrogAwardsDetailMapper {
	
	public int insertAwardDetail(FrogAwardsDetail frogAwardsDetail);
	
	public FrogAwardsDetail selectAwardDetail(Integer user_id);
	
	public int updateDetail(FrogAwardsDetail frogAwardsDetail);
}

