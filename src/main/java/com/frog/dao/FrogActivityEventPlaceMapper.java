package com.frog.dao;

import java.util.List;

import com.frog.model.FrogActivityEventPlace;
import com.frog.model.FrogTask;

public interface FrogActivityEventPlaceMapper {
	FrogActivityEventPlace selectFrogActivityEventPlaceById(Integer id);
	List<FrogActivityEventPlace> selectFrogActivityEventPlaceByEventId(Integer eventid);
	List<FrogTask> selectAllTaskInfoByEvent(Integer eventId);
	Integer insertEventPlace(FrogActivityEventPlace place);
}
