package com.frog.dao;

import java.util.List;

import com.frog.model.TaskAward;

public interface TaskAwardMapper {
	public void insertTaskAward(TaskAward award);
	public void deleteTaskAwardById(Integer id);
	public void updateTaskAwardById(TaskAward award);
	public List<TaskAward> getAllTaskAwards(Integer page);
	public TaskAward getTaskAwardById(Integer id);
	public List<TaskAward> getActivityAwardsByActivityId(Integer activityid);
}
