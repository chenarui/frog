package com.frog.serviceImpl.manage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.frog.common.Result.ApiResult;
import com.frog.dao.FrogGoodMapper;
import com.frog.model.FrogGoods;
import com.frog.service.manage.ManageService;

public class ManagerServiceImpll implements ManageService{

	@Autowired
	private FrogGoodMapper frogGoodMapper;
	
	@Override
	public ApiResult getAllGoods(String goodsName, Integer page) {
		ApiResult apiResult= new ApiResult();
		HashMap<String, Object> map=new HashMap<>();
	List<FrogGoods> goodsList=frogGoodMapper.selectAllGoods(goodsName, (page-1)*5);
	int count=frogGoodMapper.selectGoodsCount(goodsName);
	map.put("goodsList", goodsList);
	map.put("count", count);
	//当前页数
	if (count % 5==0) {
		map.put("totalPages", count/5);
	}else {
		map.put("totalPages", (count/5)+1);
	}
	map.put("page", page);
	apiResult.setResult(map);
		return apiResult;
	}

	@Override
	public ApiResult delGoods(Integer id) {
		frogGoodMapper.delGoods(id);
		return new ApiResult();
	}

	@Override
	public ApiResult getGoodsInfo(Integer id) {
		FrogGoods frogGoods=frogGoodMapper.selectGoodsInfo(id);
		if (frogGoods==null ) {
			return new ApiResult(2, "没有该商品信息");
		}
		 ApiResult apiResult= new ApiResult();
		 
	    Map<String, Object> map= new HashMap<>();
	    //int num =  frogGoodMapper.updateGoodByNum(frogGoods);
	    map.put("frogGoods", frogGoods);
	   // map.put("num", num);
	    apiResult.setResult(map);
		return apiResult;
	}

	@Override
	public ApiResult updateGoods(FrogGoods frogGoods) {
		frogGoodMapper.updateGoodsInfo(frogGoods);
		return new ApiResult();
	}


	@Override
	public ApiResult getGoodsByType(String type) {
		// TODO Auto-generated method stub
		;
		ApiResult apiResult= new ApiResult();
		apiResult.setResult(frogGoodMapper.selectGoodsByType(type));
//		HashMap<String, Object> map=new HashMap<>();
//	List<FrogGoods> goodsList=frogGoodMapper.selectAllGoods(type, (page-1)*5);
//	int count=frogGoodMapper.selectGoodsCount(type);
//	map.put("goodsList", goodsList);
//	map.put("count", count);
//	//当前页数
//	if (count % 5==0) {
//		map.put("totalPages", count/5);
//	}else {
//		map.put("totalPages", (count/5)+1);
//	}
//	map.put("page", page);
//	apiResult.setResult(map);
		return apiResult;
	}

	@Override
	public ApiResult insertGoods(String good_name, String path, BigDecimal balance, BigDecimal price, String good_info,
			String type) {
		// TODO Auto-generated method stub
		FrogGoods frogGoods= new FrogGoods();
		frogGoods.setGood_name(good_name);
		frogGoods.setPath(path);
		frogGoods.setBalance(balance);
		frogGoods.setPrice(price);
		frogGoods.setGood_info(good_info);
		frogGoods.setDel(false);
		frogGoods.setAdd_time(new Date());
		frogGoods.setType(type);
		frogGoodMapper.insert(frogGoods);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("good_id", frogGoods.getGood_id());
		ApiResult apiResult
		=new ApiResult();
		apiResult.setResult(map);
		return apiResult;
	}

	@Override
	public List<FrogGoods> getFrogGoods(String good_name) {
		// TODO Auto-generated method stub
//		ApiResult apiResult= new ApiResult();
		//apiResult.setResult();
		return frogGoodMapper.selectFrogGoods(good_name);
	}

	@Override
	public int updateGoodByNum(FrogGoods frogGoods) {
		// TODO Auto-generated method stub
		return frogGoodMapper.updateGoodByNum(frogGoods);
	}

	@Override
	public ApiResult selectGoodByNum(FrogGoods frogGoods) {
		// TODO Auto-generated method stub
		ApiResult apiResult= new ApiResult();
		apiResult.setResult(frogGoodMapper.selectGoodByNum(frogGoods));
		return apiResult;
	}

	

	
}
