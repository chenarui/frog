package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.SysConfigMapper;
import com.frog.model.SysConfig;
import com.frog.service.SysConfigService;

public class SysConfigServiceImpl implements SysConfigService{
	
	@Autowired
	private SysConfigMapper sysConfigMapper;
	@Override
	public List<SysConfig> selectSysConfByKey(String key) {
		return sysConfigMapper.selectSysConfByKey(key);
	}
	@Override
	public int updateSysConf(SysConfig sysConfig) {
		// TODO Auto-generated method stub
		return sysConfigMapper.updateSysConf(sysConfig);
	}
	
}
