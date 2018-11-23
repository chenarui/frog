package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.FrogActivityAwardHisMapper;
import com.frog.model.FrogActivityAwardHis;
import com.frog.service.FrogActivityAwardHisService;

public class FrogActivityAwardHisServiceImpl implements FrogActivityAwardHisService{

	@Autowired
	private FrogActivityAwardHisMapper mapper;
	@Override
	public void updateFAAHS(FrogActivityAwardHis his) {
		// TODO Auto-generated method stub
		 mapper.updateFrogActivityAwardHis(his);
	}

	@Override
	public int insertFAAHS(FrogActivityAwardHis his) {
		// TODO Auto-generated method stub
		return mapper.insertFrogActivityAwardHis(his);
	}

	@Override
	public List<FrogActivityAwardHis> getFAAHSByUseridAndState(Integer user_id, String state) {
		// TODO Auto-generated method stub
		return mapper.selectAllAwardsByUserId(user_id, state);
	}

}
