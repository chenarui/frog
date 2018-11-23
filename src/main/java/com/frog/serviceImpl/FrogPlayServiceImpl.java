package com.frog.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.frog.common.Result.ApiResult;
import com.frog.dao.FrogPlayMapper;
import com.frog.model.FrogPlay;
import com.frog.service.FrogPlayService;

public class FrogPlayServiceImpl implements FrogPlayService{

	@Autowired
	private FrogPlayMapper mapper;
	
	

	@Override
	public List<FrogPlay> getFrogPlay(FrogPlay frogplay) {
		// TODO Auto-generated method stub
		return mapper.selectFrogPlay(frogplay);
	}

	@Override
	public int saveFrogPlay(String playname, String diff, String theme, String ref, String additional, String wire,
			Integer price, String address, String pic, Integer status) {
		// TODO Auto-generated method stub
		FrogPlay frogPlay = new FrogPlay();
		frogPlay.setPlayname(playname);
		frogPlay.setDiff(diff);
		frogPlay.setTheme(theme);
		frogPlay.setRef(ref);
		frogPlay.setAdditional(additional);
		frogPlay.setWire(wire);
		frogPlay.setPic(pic);
		frogPlay.setPrice(price);
		frogPlay.setAddress(address);
		frogPlay.setStatus(status);
		return mapper.addFrogPlay(frogPlay);
	}

	@Override
	public int delFrogPlayById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delFrogPlayById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public int updateFrogPlayById(FrogPlay frogplay) {
		// TODO Auto-generated method stub
		return mapper.updateFrogPlayById(frogplay);
	}

	@Override
	public ApiResult getFrogPlayInfo(Integer id) {
		FrogPlay frogplay = mapper.selectFrogPlayInfo(id);
		ApiResult result= new ApiResult();
		if(frogplay == null){
			return new ApiResult(2, "没有该商品信息");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("frogplay", frogplay);
		result.setResult(map);
		// TODO Auto-generated method stub
		return result;
	}
	
	 

	

	

	

	

	

}
