package com.frog.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.frog.common.Result.ApiResult;
import com.frog.dao.FrogUserAwardsMapper;
import com.frog.dao.TakeTaskInfoMapper;
import com.frog.dao.TaskInfoMapper;
import com.frog.dao.UserChipMapper;
import com.frog.dao.UserInfoMapper;
import com.frog.model.FrogAwards;
import com.frog.model.FrogTake;
import com.frog.model.FrogTask;
import com.frog.model.UserChip;
import com.frog.model.UserInfo;
import com.frog.redis.RedisCache;
import com.frog.service.FrogActivityService;
import com.frog.service.UserService;

@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private RedisCache redisCache;

	@Autowired
	private FrogUserAwardsMapper frogUserAwardsMapper;

	@Autowired
	private TakeTaskInfoMapper takeTaskInfoMapper;
	@Autowired
	private TaskInfoMapper taskInfoMapper;
	
	@Autowired
	private UserChipMapper userChipMapper;

	@Autowired
	private FrogActivityService frogAService;
	@Override
	public List<UserInfo> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserInfo viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public UserInfo save(UserInfo t) {
		userInfoMapper.insert(t);
		return t;
	}

	@Override
	public boolean update(UserInfo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo selectUserInfoByPhone(String phone) {

		return userInfoMapper.getUserInfoByPhone(phone);
	}

	@Override
	@Transactional(readOnly = false)
	public int login(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo);
	}

	@Override
	public UserInfo getUserInfoByOpenId(String openId) {
		return userInfoMapper.selectUserInfoByOpenId(openId);
	}

	@Override
	@Transactional(readOnly = false)
	public int updateBalance(BigDecimal balance, Integer user_id) {
		return userInfoMapper.updateBalance(balance, user_id);
	}

	@Override
	public UserInfo selectUserByUserId(Integer user_id) {
		return userInfoMapper.selectUserByUserId(user_id);
	}

	@Override
	@Transactional(readOnly = false)
	public int buildPhone(String phone, Integer user_id) {
		return userInfoMapper.updateUserInfoByUserId(phone, user_id);
	}

	@Override
	public UserInfo getUserInfo(Integer user_id) {
		return userInfoMapper.getUserInfoByUserId(user_id);
	}

	@Override
	public String getCode(String phone) throws Exception {

		return redisCache.getCache(phone);
	}

	@Override
	public List<FrogAwards> getMyWaCoinDesc(Integer user_id, Integer page) {
		if (page == null && page <= 0) {
			page = 1;
		}
		return frogUserAwardsMapper.selectUserAllAwards(user_id, (page - 1) * 5);
	}
	@Override
	public List<FrogAwards> selectUserAwardsByDate(String add_time){
		return frogUserAwardsMapper.selectUserAwardsByDate(add_time);
	}
	
	@Override
	public ApiResult userLoginInfo(String user_id,Integer type) {
		// 获取个人的基本信息
		UserInfo personalInfo = null;
		if(type ==1){
			personalInfo = userInfoMapper.getUserInfoByPhone(user_id);
		}else{
			personalInfo = userInfoMapper.getUserInfoByUserId(Integer.valueOf(user_id));
		}
		FrogTake frogTake = takeTaskInfoMapper.selectTasking(personalInfo.getUser_id(), (short) 0);
		Map<String, Object> map = new HashMap<String, Object>();
		if (frogTake == null) {
			// 证明没有进行中的任务
			personalInfo.setTasking(0);
		} else {
			// 证明有进行中的任务
			personalInfo.setTasking(1);
			FrogTask frogTask = taskInfoMapper.selectTaskInfo(frogTake.getTask_id());
			FrogTask lastask = taskInfoMapper.selectTaskInfo(frogTake.getLast_taskid());
			frogTask.setTask_type((short)frogTake.getType());
			if(frogTake.getType()==1){
				frogTask.setTask_type((short)1);
			}else{
				frogTask.setTask_type((short)0);
			}
			personalInfo.setUserInfoTask(frogTake, frogTask);
			map.put("task_name",frogTake.getTask_name());
			map.put("task_Info", frogTake.getTask_info());
//			map.put("task_all_info", frogTake);
			map.put("qr_code_address", frogTake.getTask_name());
			map.put("qr_code_id", frogTake.getQr_code_id());
			map.put("last_task", lastask);
			map.put("type",frogTake.getType());
		}
		ApiResult apiResult = new ApiResult();
		map.put("userInfo", personalInfo);
		map.put("activity", frogAService.getNowFrogActivity());
		System.out.println(personalInfo.getPic_url());
		apiResult.setResult(map);
		return apiResult;
	}

	@Override
	public UserInfo getUserInfoByPhone(String phone) {
		// TODO Auto-generated method stub
		return userInfoMapper.getUserInfoByPhone(phone);
	}

	@Override
	public UserInfo selectUserInfoByInviteId(Integer invite_id) {
		return userInfoMapper.selectUserInfoByInviteId(invite_id);
	}

	@Override
	@Transactional(readOnly = false)
	public int updateInviteID(Integer invite_id, Integer user_id) {
		return userInfoMapper.updateInviteID(invite_id, user_id);
	}

	@Override
	@Transactional(readOnly = false)
	public int updateLoginType(Integer first_login, Integer user_id) {
		return userInfoMapper.updateLoginType(first_login, user_id);
	}

	@Override
	@Transactional(readOnly = false)
	public int updateUserNextQrCode(Integer user_id, String qrCode) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateUserInfo(qrCode, user_id);
	}

	@Override
	public UserInfo getUserInfoByUnionId(String unionId) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUserInfoByUnionId(unionId);
	}

	@Transactional(readOnly = false)
	@Override
	public int updateUserNickName(String nickName, Integer user_id) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateUserNickname(nickName, user_id);
	}

	@Transactional(readOnly = false)
	@Override
	public int updateUserPhone(String phone, Integer user_id) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateUserPhone(phone, user_id);
	}

}
