package com.frog.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogTake;
import com.frog.model.FrogTaskBalance;

public interface TaskBalanceMapper extends BaseMapper<FrogTake> {
	
	public int insertBalance(@Param("user_id")Integer user_id,@Param("balance")BigDecimal balance);
	
	public int updateBalance(@Param("balance")BigDecimal balance);
	
	public FrogTaskBalance selectBalanceByUserId(@Param("user_id")Integer user_id);
	
}
