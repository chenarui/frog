package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogTake;
import com.frog.model.FrogTask;

public interface TaskInfoMapper extends BaseMapper<FrogTask> {
	
	List<FrogTask> allTaskInfo();
	
	List<FrogTask> selectAllTaskInfo(@Param("user_id")Integer user_id,@Param("page")Integer page,@Param("status") Integer status);
	FrogTask selectByPrimaryKey(Integer id);
	
	FrogTask selectTaskInfo(@Param("task_id")Integer task_id);

	FrogTask selectTaskInfoByAddress(@Param("address")String address);

	int addTask(FrogTask task);
	int updateDel(@Param("task_id")Integer task_id,@Param("del")Integer del);
	int updateTaskInfo(FrogTask task);
	
	List<FrogTask> selectAllTaskInfos(@Param("page") Integer page);
}
