package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.RankBean;
import com.frog.model.TakeHis;

public interface StatisticsMapper {
	Integer selectThisWeekTaskNum(Integer user_id);
	Double selectThisWeekDistance(Integer user_id);
	Double selectThisWeekWaCoins(Integer user_id);
	Integer selectThisWeekOrder(Integer user_id);
	List<RankBean> selectRankThisWeek();
	List<TakeHis> selectAllTakeHiss(@Param("user_id")Integer user_id,@Param("page")Integer page,@Param("status")Integer status);
	List<TakeHis> selectAllTakeHisGoing(@Param("user_id")Integer user_id,@Param("page")Integer page);
}
