package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.FrogActivityEventPlaceMapper;
import com.frog.model.FrogActivityEventPlace;
import com.frog.model.FrogTask;
import com.frog.service.FrogActivityEventPlaceService;

public class FrogActivityEventPlaceServiceImpl implements FrogActivityEventPlaceService{

	@Autowired
	private FrogActivityEventPlaceMapper mapper;
	@Override
	public List<FrogTask> getAllFrogTasksByEventId(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectAllTaskInfoByEvent(id);
	}
	@Override
	public Integer addEventPlace(FrogActivityEventPlace place) {
		// TODO Auto-generated method stub
		return mapper.insertEventPlace(place);
	}

}
