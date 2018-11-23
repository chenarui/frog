package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogActivityUser;
import com.frog.model.UserInfo;

public interface FrogActivityUserMapper {
	List<UserInfo> selectAllFrogActivityUsers(@Param("state")Integer state,@Param("activityid")Integer activityid);
	List<UserInfo> selectAllFrogActivityUsersNoState(Integer activityid);
	Integer selectFrogActivityUsersCount(Integer activityid);
	FrogActivityUser selectNowUserEvent(@Param("userid")Integer userid,@Param("activityid")Integer activityid);
	void insertFrogActivityUser(FrogActivityUser user);
	void updateActivityUserState(@Param("state")Integer state,@Param("userid") Integer userid,@Param("activityid")Integer activityid);
	void updateActivityUserStep(@Param("eventstep")Integer eventstep,@Param("userid") Integer userid,@Param("activityid")Integer activityid);
	FrogActivityUser selectUserNowActivity(@Param("userid")Integer userid);
}
