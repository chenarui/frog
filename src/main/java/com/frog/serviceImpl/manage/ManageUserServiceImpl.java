package com.frog.serviceImpl.manage;

import org.springframework.transaction.annotation.Transactional;

import com.frog.dao.BaseMapper;
import com.frog.service.manage.ManageUserService;
import com.frog.serviceImpl.BaseServiceImpl;

@Transactional(readOnly = true)
public class ManageUserServiceImpl extends BaseServiceImpl implements ManageUserService{

	@Override
	public BaseMapper getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
