package com.frog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.ExchangeInfoMapper;
import com.frog.dao.FrogAwardsDetailMapper;
import com.frog.model.FrogAwardsDetail;
import com.frog.service.FrogAwardsDetailService;

public class FrogAwardsDetailServiceImpl implements FrogAwardsDetailService{
	
	@Autowired
	private FrogAwardsDetailMapper frogAwardsDetailMapper;

	@Override
	public int insertAwardDetail(FrogAwardsDetail frogAwardsDetail) {
		return frogAwardsDetailMapper.insertAwardDetail(frogAwardsDetail);
	}

	@Override
	public FrogAwardsDetail selectAwardDetail(Integer user_id) {
		return frogAwardsDetailMapper.selectAwardDetail(user_id);
	}

	@Override
	public int updateDetail(FrogAwardsDetail frogAwardsDetail) {
		return frogAwardsDetailMapper.updateDetail(frogAwardsDetail);
	}

}
