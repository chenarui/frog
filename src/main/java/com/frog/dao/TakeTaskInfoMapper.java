package com.frog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogTake;

public interface TakeTaskInfoMapper extends BaseMapper<FrogTake> {
	public int insert(@Param("frogTake") FrogTake frogTake,@Param("take_id")Integer take_id);
	
	public int operateTakeTask(@Param("status")short status,@Param("foot_step")Integer foot_step,@Param("distance")Double distance ,@Param("type")Integer type,@Param("take_id")Integer take_id);

	public FrogTake selectTakeByTakeId(@Param("take_id")Integer take_id);
	
	int selectNoCompleteTask(Map<String, Object> map);
	
	FrogTake selectTakeInfoBytakeId(@Param("take_id")Integer take_id);
	
	List<FrogTake> selectAllMyTask(@Param("user_id")Integer user_id,@Param("status")short status,@Param("page")Integer page);
    
	List<FrogTake> selectNowTask(@Param("user_id")Integer user_id,@Param("status")short status);
	
	FrogTake selectTakeInfo(@Param("take_id")Integer take_id);
	
	FrogTake selectUserTaskInfoByStatus(Map<String, Object> map);
	
	FrogTake selectTasking(@Param("user_id")Integer user_id,@Param("status")short status);
}
