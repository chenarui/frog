package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.FrogAwardMapper;
import com.frog.model.FrogAward;
import com.frog.service.FrogAwardService;
import com.frog.service.FrogAwardsDetailService;

public class FrogAwardServiceImpl implements FrogAwardService{
	
	@Autowired
	private FrogAwardMapper frogAwardMapper;
	@Override
	public List<FrogAward> selectFrogAwardByUser() {
		return	frogAwardMapper.selectFrogAwardByUser();
	}
	@Override
	public FrogAward selectByAwardId(Integer award_id) {
		return frogAwardMapper.selectByAwardId(award_id);
	}
	
}
