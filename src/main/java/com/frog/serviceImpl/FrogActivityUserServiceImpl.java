package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.FrogActivityUserMapper;
import com.frog.model.FrogActivityUser;
import com.frog.model.UserInfo;
import com.frog.service.FrogActivityUserService;

public class FrogActivityUserServiceImpl implements FrogActivityUserService{

	@Autowired
	private FrogActivityUserMapper mapper;
	@Override
	public Integer getAllUserCountByActivity(Integer activityid) {
		// TODO Auto-generated method stub
		return mapper.selectFrogActivityUsersCount(activityid);
	}

	@Override
	public List<UserInfo> getAllUserByActivity(Integer activityid) {
		// TODO Auto-generated method stub
		return mapper.selectAllFrogActivityUsersNoState(activityid);
	}

	@Override
	public FrogActivityUser getFrogActivityUser(Integer userId, Integer activityId) {
		// TODO Auto-generated method stub
		return mapper.selectNowUserEvent(userId, activityId);
	}

	@Override
	public void addFrogActivityUser(FrogActivityUser user) {
		// TODO Auto-generated method stub
		mapper.insertFrogActivityUser(user);
	}

	@Override
	public void updateUserState(Integer state,Integer userid,Integer activityId) {
		// TODO Auto-generated method stub
		mapper.updateActivityUserState(state, userid,activityId);
	}

	@Override
	public FrogActivityUser getFrogActivityByUser(Integer userId) {
		// TODO Auto-generated method stub
		return mapper.selectUserNowActivity(userId);
	}
}
