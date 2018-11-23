package com.frog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogGoods;

public interface FrogGoodMapper extends BaseMapper<FrogGoods>{
	public FrogGoods selectGoodByGoodId(@Param("good_id") Integer good_id);

	public List<FrogGoods> selectGoods();
	
	public List< FrogGoods> selectAllGoods(@Param("good_name")String good_name,@Param("page")Integer page);

     public Integer selectGoodsCount(@Param("good_name")String good_name);
     
     public int delGoods(@Param("good_id")Integer good_id);
     
     public FrogGoods selectGoodsInfo(@Param("good_id") Integer good_id);
     
     public int updateGoodsInfo(FrogGoods frogGoods);
     
     public int insertGoodsInfo(FrogGoods	frogGoods);
     
     int insert(FrogGoods frogGoods);
     
     //模糊查询
     List<FrogGoods> selectFrogGoods(@Param("good_name")String good_name);
     //分类
     List<FrogGoods> selectGoodsByType(String type);
     //更改次数
     int updateGoodByNum(FrogGoods frogGoods);
     //点击量
     List<FrogGoods> selectGoodByNum(FrogGoods frogGoods);
}
