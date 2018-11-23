package com.frog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.frog.dao.UserAddressMapper;
import com.frog.model.UserAddress;
import com.frog.service.UserAddressService;
//@Transactional(readOnly = true)
public class UserAddressServiceImpl implements UserAddressService{
	@Autowired
	private UserAddressMapper userAddressMapper;

	@Override
	public List<UserAddress> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserAddress viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserAddress save(UserAddress t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UserAddress t) {
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
	public int insertAddress(Integer user_id, String name, String phone, String address,Date create_date) {
		return userAddressMapper.insertAddress(user_id, name, phone, address,create_date);
	}

	@Override
	public List<UserAddress> selectAddressfromUser(Integer user_id) {
		return userAddressMapper.selectAddressfromUser(user_id);
	}
	
}
