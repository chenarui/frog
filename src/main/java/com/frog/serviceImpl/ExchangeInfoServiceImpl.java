package com.frog.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.frog.dao.ExchangeInfoMapper;
import com.frog.model.ExchangeInfo;
import com.frog.service.ExchangeInfoService;

public class ExchangeInfoServiceImpl implements ExchangeInfoService{
	@Autowired
	private ExchangeInfoMapper exchangeInfoMapper;
	
	@Override
	public List<ExchangeInfo> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ExchangeInfo viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ExchangeInfo save(ExchangeInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ExchangeInfo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExchangeInfo> selectExchangeInfoUserID(Integer user_id) {
		return exchangeInfoMapper.selectExchangeInfoUserID(user_id);
	}

	@Override
	@Transactional(readOnly = false)
	public Integer insertExchangeInfo(ExchangeInfo exchangeInfo) {
		return exchangeInfoMapper.insertExchangeInfo(exchangeInfo);
	}
	
}
