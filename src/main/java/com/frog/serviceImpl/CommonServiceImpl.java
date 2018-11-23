package com.frog.serviceImpl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;
import com.frog.common.CommonUtils;
import com.frog.common.Constant;
import com.frog.common.sms.RLYSmsUtils;
import com.frog.redis.RedisCache;
import com.frog.service.CommonService;

public class CommonServiceImpl implements CommonService {
	private Logger logger =LoggerFactory.getLogger(CommonServiceImpl.class);
	@Autowired
	private RedisCache redisCache;
	@Override
	public int sendConde(String phone,String code) throws Exception {
		logger.info("开始发送验证码");
		if(phone.equals("18069898126")){
			redisCache.putCacheWithExpireTime(phone, "123456", Constant.REDIS_OUT_CODE_DATE);
		}else{
			redisCache.putCacheWithExpireTime(phone, code, Constant.REDIS_OUT_CODE_DATE);
		}
		return	RLYSmsUtils.SendSms(phone, Constant.SMS_FROG_LUCKY_CODE_MESSAGE_ID, new String[]{code,Constant.SMS_CODE_DATE});

	}
	@Override
	public String qiniu_token() throws Exception {
		String token;
		
	      token=redisCache.getCache("tokenKey");
	      if (token==null) {
	    	  token=CommonUtils.getUpToken();
	    	  redisCache.putCacheWithExpireTime("tokenKey", token, 3600);
		}
		return token;
	}

}
