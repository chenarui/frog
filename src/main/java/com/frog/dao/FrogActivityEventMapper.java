package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogActivityEvent;
import com.frog.model.TaskAward;

public interface FrogActivityEventMapper {
	int addFrogActivityEvent(FrogActivityEvent event);
	void deleteFrogActivityEventById(Integer id);
	FrogActivityEvent selectFrogActivityEventById(Integer id);
	List<FrogActivityEvent> selectAllActivityEvents();
	void updateActivityEvent(FrogActivityEvent event); 
	List<FrogActivityEvent> seletcFrogActivityEventsByActivityId(Integer activityid);
	TaskAward selectAwardByEnent(Integer eventId);
	
	int addEventPic(FrogActivityEvent event);
	
	List<FrogActivityEvent> selectEventByDiff(Integer diff);
	
	List<FrogActivityEvent> selectEventByDiffTheme(@Param("diff")Integer diff,@Param("theme")Integer theme);
	
	List<FrogActivityEvent> selectEvents(FrogActivityEvent event);
	
	
	
}
