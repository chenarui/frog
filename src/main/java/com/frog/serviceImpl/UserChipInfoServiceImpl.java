package com.frog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.UserChipInfoMapper;
import com.frog.model.UserChipInfo;
import com.frog.service.UserChipInfoService;

public class UserChipInfoServiceImpl implements UserChipInfoService{

	@Autowired
	private UserChipInfoMapper userChipInfoMapper;
	@Override
	public List<UserChipInfo> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserChipInfo viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserChipInfo save(UserChipInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UserChipInfo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertUserChipInfo(UserChipInfo userChipInfo) {
		return userChipInfoMapper.insertUserChipInfo(userChipInfo);
	}

	@Override
	public List<UserChipInfo> selectUserChipInfoByUser(Integer user_id) {
		return userChipInfoMapper.selectUserChipInfoByUser(user_id);
	}

	@Override
	public int selectCountByDate(Integer user_id, String add_time) {
		return userChipInfoMapper.selectCountByDate(user_id, "%"+add_time+"%");
	}

	@Override
	public int selectCountByDateOnePerson(Integer user_id, String add_time, Integer ano_user_id) {
		return userChipInfoMapper.selectCountByDateOnePerson(user_id, "%"+add_time+"%", ano_user_id);
	}
}
