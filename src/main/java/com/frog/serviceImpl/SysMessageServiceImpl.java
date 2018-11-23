package com.frog.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.SysMessageMapper;
import com.frog.model.SysMessage;
import com.frog.service.SysMessageService;

public class SysMessageServiceImpl implements SysMessageService{
	
	@Autowired
	private SysMessageMapper sysMessageMapper;

	@Override
	public List<SysMessage> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysMessage viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SysMessage save(SysMessage t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(SysMessage t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysMessage> selectSysMessage() {
		return sysMessageMapper.selectSysMessage();
	}

	@Override
	public int insertMessage(SysMessage sysMessage) {
		return sysMessageMapper.insertMessage(sysMessage);
	}

	@Override
	public List<SysMessage> selectSysMessageByUser(Integer user_id) {
		return sysMessageMapper.selectSysMessageByUser(user_id);
	}

	@Override
	public int updateAddressType(SysMessage sysMessage) {
		return sysMessageMapper.updateAddressType(sysMessage);
	}
	
}
