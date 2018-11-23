package com.frog.dao;

import java.util.List;

import com.frog.model.SysConfig;

public interface SysConfigMapper {
	public List<SysConfig>selectSysConfByKey(String key);
	
	public int updateSysConf(SysConfig sysConfig);
}
