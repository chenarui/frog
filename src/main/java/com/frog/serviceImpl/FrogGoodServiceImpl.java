package com.frog.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.FrogGoodMapper;
import com.frog.model.FrogGoods;
import com.frog.service.FrogGoodService;

public class FrogGoodServiceImpl implements FrogGoodService {
	@Autowired
	private FrogGoodMapper frogGoodMapper;

	@Override
	public FrogGoods selectGoodByGoodId(Integer good_id) {
		return frogGoodMapper.selectGoodByGoodId(good_id);
	}

	@Override
	public List<FrogGoods> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FrogGoods viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FrogGoods save(FrogGoods t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(FrogGoods t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FrogGoods> selectGoods() {
		return frogGoodMapper.selectGoods();
	}
	
}
