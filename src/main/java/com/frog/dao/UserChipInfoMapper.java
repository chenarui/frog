package com.frog.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.UserChipInfo;

public interface UserChipInfoMapper {
	public int insertUserChipInfo(UserChipInfo userChipInfo);
	
	public List<UserChipInfo> selectUserChipInfoByUser(Integer user_id);
	
	public int selectCountByDate(@Param("user_id")Integer user_id,@Param("add_time")String  add_time);
	
	public int selectCountByDateOnePerson(@Param("user_id")Integer user_id,@Param("add_time")String  add_time,@Param("ano_user_id")Integer ano_user_id);
}
