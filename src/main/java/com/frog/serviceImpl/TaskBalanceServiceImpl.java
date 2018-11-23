package com.frog.serviceImpl;

import java.math.BigDecimal;

import com.frog.dao.TaskBalanceMapper;
import com.frog.model.FrogTaskBalance;
import com.frog.service.TaskBalanceService;

public class TaskBalanceServiceImpl implements TaskBalanceService{
	private TaskBalanceMapper taskBalanceMapper; 
	@Override
	public int insertBalance(int user_id, BigDecimal balance) {
		return taskBalanceMapper.insertBalance(user_id, balance);
	}

	@Override
	public int updateBalance(BigDecimal balance) {
		return taskBalanceMapper.updateBalance(balance);
	}

	@Override
	public FrogTaskBalance selectBalanceByUserId(Integer user_id) {
		return taskBalanceMapper.selectBalanceByUserId(user_id);
	}

}
