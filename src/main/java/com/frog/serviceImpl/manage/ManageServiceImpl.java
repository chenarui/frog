//package com.frog.serviceImpl.manage;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.frog.common.Result.ApiResult;
//import com.frog.dao.FrogGoodMapper;
//import com.frog.model.FrogGoods;
//import com.frog.service.manage.ManageService;
//
//@Transactional(readOnly = true)
////public class ManageServiceImpl   implements ManageService{
//@Autowired
//private FrogGoodMapper frogGoodMapper;
//
//	@Override
//	public ApiResult getAllGoods(String goodsName,Integer page) {
//		ApiResult apiResult= new ApiResult();
//		HashMap<String, Object> map=new HashMap<>();
//	List<FrogGoods> goodsList=frogGoodMapper.selectAllGoods(goodsName, (page-1)*5);
//	int count=frogGoodMapper.selectGoodsCount(goodsName);
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
//		return apiResult;
//	}
//
//	@Override
//	@Transactional(readOnly=false)
//	public ApiResult delGoods(Integer id) {
//		frogGoodMapper.delGoods(id);
//		return new ApiResult();
//	}
//
//	@Override
//	public ApiResult getGoodsInfo(Integer id) {
//		FrogGoods frogGoods=frogGoodMapper.selectGoodsInfo(id);
//		if (frogGoods==null ) {
//			return new ApiResult(2, "没有该商品信息");
//		}
//		 ApiResult apiResult= new ApiResult();
//		 
//	    Map<String, Object> map= new HashMap<>();
//	    map.put("frogGoods", frogGoods);
//	    apiResult.setResult(map);
//		return apiResult;
//	}
//
//	@Override
//	@Transactional(readOnly=false)
//	public ApiResult updateGoods(FrogGoods frogGoods) {
//		frogGoodMapper.updateGoodsInfo(frogGoods);
//		return new ApiResult();
//	}
//
//	@Override
//	@Transactional(readOnly=false)
//	public ApiResult insertGoods(String good_name,String path,BigDecimal balance,BigDecimal price,String good_info) {
//		FrogGoods frogGoods= new FrogGoods();
//		frogGoods.setGood_name(good_name);
//		frogGoods.setPath(path);
//		frogGoods.setBalance(balance);
//		frogGoods.setPrice(price);
//		frogGoods.setGood_info(good_info);
//		frogGoods.setDel(false);
//		frogGoods.setAdd_time(new Date());
//		frogGoodMapper.insert(frogGoods);
//		Map<String, Object> map = new HashMap<String,Object>();
//		map.put("good_id", frogGoods.getGood_id());
//		ApiResult apiResult
//		=new ApiResult();
//		apiResult.setResult(map);
//		return apiResult;
//	}
//
//	//@Transactional(readOnly=false)
//	@Override
//	public int getFrogGoods(String good_name) {
//		// TODO Auto-generated method stub
//		return frogGoodMapper.selectFrogGoods(good_name);
//	}
//
//	//@Transactional(readOnly=false)
//	@Override
//	public List<FrogGoods> getGoodsByType(String type) {
//		// TODO Auto-generated method stub
//		return frogGoodMapper.selectGoodsByType(type);
//	}
//}
