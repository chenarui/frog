package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.dao.StatisticsMapper;
import com.frog.model.RankBean;
import com.frog.model.TakeHis;
import com.frog.service.StatisticsService;

public class StaticticServiceImpl implements StatisticsService{

	@Autowired
	StatisticsMapper mapper;
	@Override
	public Integer getTaskNumThisWeek(Integer user_id) {
		// TODO Auto-generated method stub
		return mapper.selectThisWeekTaskNum(user_id);
	}

	@Override
	public Double getThisWeekDistance(Integer user_id) {
		// TODO Auto-generated method stub
		return mapper.selectThisWeekDistance(user_id);
	}

	@Override
	public Double getThisWeekWaCoins(Integer user_id) {
		// TODO Auto-generated method stub
		return mapper.selectThisWeekWaCoins(user_id);
	}

	@Override
	public Integer getThisWeekOrder(Integer user_id) {
		// TODO Auto-generated method stub
		return mapper.selectThisWeekOrder(user_id);
	}

	@Override
	public List<RankBean> getOrderRankBeans() {
		// TODO Auto-generated method stub
		return mapper.selectRankThisWeek();
	}

	@Override
	public List<TakeHis> getAllTakeHiss(Integer user_id,Integer page, Integer status) {
		// TODO Auto-generated method stub
		return mapper.selectAllTakeHiss(user_id,page*10,status);
	}

}
