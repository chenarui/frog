package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.TaskAwardMapper;
import com.frog.model.TaskAward;
import com.frog.service.TaskAwardService;

public class TaskAwardServerImpl implements TaskAwardService{

	@Autowired
	private TaskAwardMapper mapper;

	@Override
	public List<TaskAward> getAllTaskAward(Integer page) {
		// TODO Auto-generated method stub
		return mapper.getAllTaskAwards(page);
	}

	@Override
	public TaskAward getTaskAwardById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getTaskAwardById(id);
	}

	@Override
	public void deleteTaskAward(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteTaskAwardById(id);
	}

	@Override
	public void upDateTaskAward(TaskAward award) {
		// TODO Auto-generated method stub
		mapper.updateTaskAwardById(award);
	}

	@Override
	public List<TaskAward> getAllActivityAwardsByActivityId(Integer activityid) {
		// TODO Auto-generated method stub
		return mapper.getActivityAwardsByActivityId(activityid);
	}

}
