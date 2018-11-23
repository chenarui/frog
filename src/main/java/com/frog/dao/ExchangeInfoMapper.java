package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.ExchangeInfo;

public interface ExchangeInfoMapper {
	public List<ExchangeInfo> selectExchangeInfoUserID(@Param("user_id")Integer user_id);
	
	public Integer insertExchangeInfo(ExchangeInfo exchangeInfo);
}
