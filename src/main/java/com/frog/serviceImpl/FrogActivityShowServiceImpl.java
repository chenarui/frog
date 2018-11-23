package com.frog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.FrogActivityShowMapper;
import com.frog.model.FrogActivityShow;
import com.frog.service.FrogActivityShowService;

public class FrogActivityShowServiceImpl implements FrogActivityShowService{

	@Autowired
	private FrogActivityShowMapper mapper;
	@Override
	public FrogActivityShow selectUserShowed(Integer user_id, Integer activityid) {
		// TODO Auto-generated method stub
		return mapper.selectByUseridAndActivityId(user_id, activityid);
	}

	@Override
	public void insertFrogActivityShow(FrogActivityShow show) {
		// TODO Auto-generated method stub
		mapper.insert(show);
	}

}
