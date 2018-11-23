package com.frog.dao;

import java.util.List;

import com.frog.model.FrogActivityAward;

public interface FrogActivityAwardMapper {
	List<FrogActivityAward> getAllAwards(Integer activityId);
	void updateAwards(FrogActivityAward award);
}
