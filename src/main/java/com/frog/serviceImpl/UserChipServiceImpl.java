package com.frog.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.frog.dao.UserChipMapper;
import com.frog.model.UserChip;
import com.frog.service.UserChipService;

public class UserChipServiceImpl implements UserChipService{
	
	@Autowired
	private UserChipMapper userChipMapper;

	@Override
	public List<UserChip> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserChip viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserChip save(UserChip t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UserChip t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer insert(UserChip userChip) {
		return userChipMapper.insert(userChip);
	}

	@Override
	@Transactional(readOnly = false)
	public Integer updateChip(UserChip userChip) {
		// TODO Auto-generated method stub
		return userChipMapper.updateChip(userChip);
	}

	@Override
	public UserChip selectUserChipByUser(Integer user_id) {
		return userChipMapper.selectUserChipByUser(user_id);
	}
	
	
	
	
}
