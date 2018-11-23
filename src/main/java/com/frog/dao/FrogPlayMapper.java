package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogPlay;

public interface FrogPlayMapper {
	
	int addFrogPlay(FrogPlay frogplay);
	
	int delFrogPlayById(@Param("id")Integer id);
	
	List<FrogPlay> selectFrogPlay(FrogPlay frogplay);
	
	int updateFrogPlayById(FrogPlay frogplay);
	
	FrogPlay selectFrogPlayInfo(@Param("id")Integer id);

}
