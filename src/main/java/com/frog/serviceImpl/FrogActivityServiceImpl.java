package com.frog.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.FrogActivity;
import com.frog.model.FrogActivityBean;
import com.frog.service.FrogActivityService;

public class FrogActivityServiceImpl implements FrogActivityService{

	@Autowired
	private FrogActivity frogActivity;
	@Override
	public FrogActivityBean getNowFrogActivity() {
		// TODO Auto-generated method stub
		return frogActivity.getNowFrogActivity(0);
	}
	@Override
	public FrogActivityBean getFrogActivityById(Integer id) {
		// TODO Auto-generated method stub
		return frogActivity.getFrogActivityById(id);
	}
}
