package com.frog.serviceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.frog.common.Arith;
import com.frog.common.CommonUtils;
import com.frog.common.Constant;
import com.frog.common.LocationUtils;
import com.frog.common.VocalConcertUtil;
import com.frog.common.Result.ApiResult;
import com.frog.dao.FrogActivityAwardHisMapper;
import com.frog.dao.FrogActivityAwardMapper;
import com.frog.dao.FrogActivityEventMapper;
import com.frog.dao.FrogActivityEventPlaceMapper;
import com.frog.dao.FrogActivityUserMapper;
import com.frog.dao.FrogUserAwardsMapper;
import com.frog.dao.QrMapper;
import com.frog.dao.TakeTaskInfoMapper;
import com.frog.dao.TaskAwardMapper;
import com.frog.dao.TaskInfoMapper;
import com.frog.dao.UserChipInfoMapper;
import com.frog.dao.UserChipMapper;
import com.frog.dao.UserInfoMapper;
import com.frog.model.FrogActivityAward;
import com.frog.model.FrogActivityAwardHis;
import com.frog.model.FrogActivityUser;
import com.frog.model.FrogAwards;
import com.frog.model.FrogRQ;
import com.frog.model.FrogTake;
import com.frog.model.FrogTask;
import com.frog.model.TaskAward;
import com.frog.model.UserChip;
import com.frog.model.UserChipInfo;
import com.frog.model.UserInfo;
import com.frog.redis.RedisCache;
import com.frog.service.TaskService;

public class TaskServiceImpl implements TaskService {
	private Logger logger = Logger.getLogger(TaskServiceImpl.class);
	@Autowired
	private TaskInfoMapper taskInfoMapper;
	@Autowired
	private TakeTaskInfoMapper takeTaskInfoMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private QrMapper qrMapper;
	@Autowired
	private RedisCache redisCache;
	@Autowired
	private UserChipMapper userChipMapper;
	@Autowired
	private UserChipInfoMapper userChipInfoMapper;
	@Autowired
	private FrogUserAwardsMapper frogUserAwardsMapper;
	@Autowired
	private FrogActivityUserMapper acUserMapper;
	@Autowired
	private FrogActivityEventPlaceMapper placeMapper;
	@Autowired
	private TaskAwardMapper taskAwardmapper;
	@Autowired
	private FrogActivityEventMapper eventMapper;
	@Autowired
	private FrogActivityAwardHisMapper faahm;
	@Autowired
	private FrogActivityAwardMapper awardMapper;
	
	@Override
	public synchronized ApiResult getTask(String qr_code_id, Integer user_id,Double start_longitude,Double start_latitude,Integer type) throws Exception {
		String key = CommonUtils.getDayKey(user_id);
		String hashKey = CommonUtils.hashKey();
		String dayCount = redisCache.getHashKey(key, hashKey);
		FrogActivityUser user = acUserMapper.selectUserNowActivity(user_id);
		List<FrogTask> activityTasks = null;
		if(user !=null){
			logger.info("用户参加活动"+user.getActivityid());
			activityTasks = placeMapper.selectAllTaskInfoByEvent(user.getNoweventid());
		}
		if (dayCount == null) {
			logger.info("用户ID{}" + user_id + ",今天第一次扫码");
			dayCount = "0";
		}
		FrogRQ frogRQ=qrMapper.selectQRInfo(qr_code_id,false);
		//任务具体信息
		if(frogRQ == null){
			return new ApiResult(3,"当前码不存在");
		}
		FrogTask taskInfo=taskInfoMapper.selectTaskInfoByAddress(frogRQ.getQr_code_address());
		if(taskInfo==null){
			return new ApiResult(3,"当前码不存在");
		}
		if (redisCache.isExist(key) == false) {
			redisCache.addHashKey(key, hashKey, dayCount);
		} else {
			if (Integer.valueOf(dayCount) > Constant.USER_SCAN_COUNT) {
				return new ApiResult(3, "您今日扫码次数已经超过上限,请明天再来");
			}
		}
		// 查看进行中的任务
		ApiResult apiResult = new ApiResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("status", 0);
		FrogTake taskTakeCount = takeTaskInfoMapper.selectUserTaskInfoByStatus(map);
		if (taskTakeCount != null && taskTakeCount.getTake_id() != null) {
			map.put("take_id", taskTakeCount.getTake_id());
			apiResult.setResult(map);
			apiResult.setCode(4);
			apiResult.setMessage("您还有进行中的任务，不能进行扫码，请完成后进行扫码");
			return apiResult;
		}
		// 去除list中已经取出来的数据
		String mlistKey = String.valueOf(user_id) + Constant.USERID_LIST;

		List<FrogTask> backlist = null;
		if (!redisCache.isExist(mlistKey)) {
			List<FrogTask> taskList = taskInfoMapper.allTaskInfo();
			redisCache.putCache(mlistKey,taskList);
		}

		List<FrogTask> redisList = redisCache.getCache(mlistKey);
		List<FrogTask> list=new ArrayList<FrogTask>();
		for (FrogTask frogTask : redisList) {
			if(!frogTask.getAddress().equals(frogRQ.getQr_code_address())){
				double distance = LocationUtils.getDistance(taskInfo.getLatitude(), taskInfo.getLongitude(), frogTask.getLatitude(), frogTask.getLongitude());
				if(distance>3*1000){
					continue;
				}
				list.add(frogTask);
			}
		}
		if(list.size()==0){
			apiResult.setCode(3);
			apiResult.setMessage("附近没有扫码点");
			return apiResult;
		}
//		List<FrogTask> list=new ArrayList<FrogTask>();
		//去掉超过三公里的点位
//		for(FrogTask frogTask:list){
//			
//		}
//		if (list.size() == 0 || list == null) {
//			List<FrogTask> taskList = taskInfoMapper.allTaskInfo();
//			//任务约束
//			list = taskList;
//		}
//
		//当用户已经报名活动
		if(user != null && activityTasks!=null &&activityTasks.size()>1){
			FrogTask orderTask = activityTasks.get(user.getEventstep());
			if(orderTask.getTask_id() != taskInfo.getTask_id()){
				return new ApiResult(3, "不是任务点");
			}
			backlist = new ArrayList<>();
			backlist.add(activityTasks.get(user.getEventstep()+1));	
			map.put("isactivitytask", 1);
		}else{
			map.put("isactivitytask", 0);
			if(type ==1){
				backlist = CommonUtils.getRandomList(list, 1);
			}else if(type ==2){
				backlist = new ArrayList<>();
				if(taskInfo.getNext_taskid()!=null&&taskInfo.getNext_taskid()!=-1){
					FrogTask task = taskInfoMapper.selectTaskInfo(taskInfo.getNext_taskid());
					if(task==null){
						System.out.println("task为空"+taskInfo.getNext_taskid());
						backlist = CommonUtils.getRandomList(list, 1);
					}else{
						backlist.add(task);
					}
				}else{
					System.out.println("next_taskid为空");
					backlist = CommonUtils.getRandomList(list, 1);
				}
			}else{
				apiResult.setCode(3);
				apiResult.setMessage("未知类型错误");
				return apiResult;
			}
		}
		
//		Iterator<FrogTask> Ilist = list.iterator();
//		while (Ilist.hasNext()) {
//			FrogTask check = Ilist.next();
//			for (FrogTask frogTask : backlist) {
//				if (check.getTask_id().equals(frogTask.getTask_id())) {
//					Ilist.remove();
//					break;
//				}
//			}
//		}
//		redisCache.delete(mlistKey);
//		redisCache.putCache(mlistKey, redisList);

		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		for (FrogTask frogTask : backlist) {
			if (frogRQ == null) {
				return new ApiResult(8, "此二维码无效");
			}
			//判断是否在码附近
			Double range=LocationUtils.getDistance(start_latitude, start_longitude, taskInfo.getLatitude(), taskInfo.getLongitude());
//			if(range>Constant.OVER_LENGTH){
//				return new ApiResult(5,"你的位置离起始码过远。无法获取任务");
//			}
//			System.out.println("起始：("+frogTask.getLatitude()+";"+frogTask.getLongitude()+")"+"终点：("+taskInfo.getLatitude()+";"+taskInfo.getLongitude()+")");
			//任务价值
			Double real_range=LocationUtils.getDistance(taskInfo.getLatitude(),taskInfo.getLongitude(), frogTask.getLatitude(), frogTask.getLongitude());
			int result=LocationUtils.getRange(real_range);
			Double total_price=0.00;
			Double task_price=0.00;
			Double hea_price=0.00;
			Double length=Arith.div(real_range, 1000, 2);
			if(result==1){
				//健康币
				hea_price=Arith.mul(length,Constant.FROG_HEA_COIN_1KM);
//				任务币
//				步行1公里，任务币3个
//				骑行1公里，任务币2个
//				驾车1公里，任务币1个
				task_price=Arith.mul(length, Constant.FROG_COIN_1KM_WALK);
				hea_price=Arith.round(hea_price, 2);
				task_price=Arith.round(task_price, 2);
				frogTask.setTask_type((short)result);
				total_price=hea_price+task_price;	
			}else if(result==2){
				hea_price=Arith.mul(length,Constant.FROG_HEA_COIN_1KM);
//				任务币
//				步行1公里，任务币3个
//				骑行1公里，任务币2个
//				驾车1公里，任务币1个
				task_price=Arith.mul(length, Constant.FROG_COIN_1KM_RUN);
				hea_price=Arith.round(hea_price, 2);
				task_price=Arith.round(task_price, 2);
				frogTask.setTask_type((short)result);
				total_price=hea_price+task_price;	
			}else{
				task_price=Arith.mul(length, Constant.FROG_COIN_1KM_DRIVE);
				task_price=Arith.round(task_price, 2);
				frogTask.setTask_type((short)result);
				total_price=task_price;	
			}
			logger.info("用户Id{}" + user_id + "正在扫码完毕");
			redisCache.addHashKey(key, hashKey, String.valueOf(Integer.valueOf(dayCount) + 1));
			FrogRQ code = qrMapper.selectQrInfoByQrCodeAddress(frogTask.getAddress(), false);
			map.put("task_pic_url", frogTask.getTask_pic_id());
			map.put("task_info", frogTask.getTask_info());
			map.put("address", frogTask.getAddress());
			map.put("task_id", frogTask.getTask_id());
			map.put("longitude", frogTask.getLongitude());
			map.put("latitude", frogTask.getLatitude());
			map.put("task_title", frogTask.getTask_title());
			map.put("task_type", frogTask.getTask_type());
			// 二维码地址
			map.put("qr_code_address", frogRQ.getQr_code_address());
			map.put("qr_longitude", taskInfo.getLongitude());
			map.put("qr_latitude", taskInfo.getLatitude());
			map.put("taskRange", real_range);
			map.put("ntask_title", taskInfo.getTask_title());
			map.put("last_taskid", taskInfo.getTask_id());
			map.put("ntask_info", taskInfo.getTask_info());
			map.put("task_price", task_price);
			map.put("hea_price", hea_price);
			map.put("total_price",total_price);
			map.put("type", result);
			map.put("qr_code_id", qr_code_id);
			map.put("qr_next_code_id", code.getQr_code_id());
			map.put("range", range);
			System.out.println("当前扫码地址："+taskInfo.getTask_title()+"下一个码的地址："+frogTask.getTask_title());
		}
		apiResult.setResult(map);
		return apiResult;
	}

	@Override
	public List<FrogTask> list(Integer page, Integer pageSize, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FrogTask viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FrogTask save(FrogTask t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(FrogTask t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFilePath(Integer fileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult allTaskInfo() {
		ApiResult apiResult = new ApiResult();
		Map<String, Object> map = new HashMap<String, Object>();
		List<FrogTask> list = taskInfoMapper.allTaskInfo();
		map.put("list", list);
		apiResult.setResult(map);
		return apiResult;
	}
	@Override
	public List<FrogTask> allTask() {
		return taskInfoMapper.allTaskInfo();
	}

	@Override
	public List<FrogTake> myTask(Integer user_id, Integer page, Integer type) {
		// List<FrogTask> list = taskInfoMapper.selectAllTaskInfo(user_id, (page
		// - 1) * 5, type);
		List<FrogTake> list = takeTaskInfoMapper.selectAllMyTask(user_id, type.shortValue(), (page - 1) * 5);
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public int operateTakeTask(Integer take_id, Integer type) {
		if (type == 1) {
			takeTaskInfoMapper.operateTakeTask((short) 1, null, null, 1, take_id);
		}
		if (type == 2) {
			takeTaskInfoMapper.operateTakeTask((short) 2, null, null, 2, take_id);
		}
		return 1;
	}

	@Override
	public FrogTake selectTakeByTakeId(Integer take_id) {
		return takeTaskInfoMapper.selectTakeByTakeId(take_id);
	}

	@Override
	@Transactional(readOnly = false)
	public ApiResult qrCompleteTask(Integer footStep, Integer user_id, Integer take_id, Integer task_id,
			String qr_code_id,String login_type,Double complete_longitude,Double complete_latitude) {
		UserInfo info = userInfoMapper.getUserInfoByUserId(user_id);
//		if(login_type==null||login_type.length()==0){
//			return new ApiResult(Code.MissParam,Msg.MissParam);
//		}
		FrogActivityUser user = acUserMapper.selectUserNowActivity(user_id);
		List<FrogTask> activityTasks = null;
		if(user !=null){
			logger.info("用户参加活动"+user.getActivityid());
			activityTasks = placeMapper.selectAllTaskInfoByEvent(user.getNoweventid());
		}
		if (!qr_code_id.equals(info.getNext_qr_code())) {
			return new ApiResult(9, "不是这个码禁止完成");
		}
		List<FrogTake> list = takeTaskInfoMapper.selectNowTask(user_id, (short) 0);
		if (list.size() <= 0) {
			return new ApiResult(8, "该任务已完成,禁止重复完成任务");
		}
		FrogTake frogTake = takeTaskInfoMapper.selectTakeInfo(take_id);
		FrogTask lastTask = taskInfoMapper.selectByPrimaryKey(frogTake.getLast_taskid());
		System.out.println("完成任务："+frogTake.getTask_name());
//		Double range=LocationUtils.getDistance(frogTake.getFinal_latitude(), frogTake.getFinal_longitude(), complete_latitude,complete_longitude);
//		System.out.println("距完成任务码距离为："+range);
//		if(range>Constant.OVER_LENGTH){
//			return new ApiResult(3,"已超出任务距离，无法完成任务");
//		}
		Double real_range=LocationUtils.getDistance(frogTake.getFinal_latitude(), frogTake.getFinal_longitude(),frogTake.getStart_latitude(),frogTake.getStart_longitude());
		Double length=Arith.div(real_range, 1000, 2);
		Double round=Arith.round(length,0);
		ApiResult apiResult = new ApiResult();
		Date complete_time=new Date();
	        //设置要获取到什么样的时间
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        //获取String类型的时间
	    Date add_time = frogTake.getAdd_time();
	    String completeTime = sdf.format(complete_time);
		String addTime=sdf.format(add_time);
		Date ct;
		Date at;
		long time = 0;
		try {
			ct = sdf.parse(completeTime);
			at=sdf.parse(addTime);
			long l=ct.getTime()-at.getTime();
			time=((l/(60*1000)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Long time=CommonUtils.getTimeDiff1(addTime, completeTime);
		//超时处理
		BigDecimal total_balance=new BigDecimal(0);
		UserInfo userInfo=userInfoMapper.selectUserByUserId(user_id);
		if((round*15)<= time){
			userInfoMapper.updateBalance(userInfo.getBalance().add(new BigDecimal(frogTake.getTask_price())), user_id);
			userInfoMapper.updateTotalBalance(userInfo.getBalance().add(new BigDecimal(frogTake.getTask_price())), user_id);
			total_balance=new BigDecimal(frogTake.getTask_price().toString());
		}else{
			userInfoMapper.updateBalance(userInfo.getBalance().add(new BigDecimal(frogTake.getTotal_price())), user_id);
			userInfoMapper.updateTotalBalance(userInfo.getBalance().add(new BigDecimal(frogTake.getTotal_price())), user_id);			
			total_balance=new BigDecimal(frogTake.getTotal_price().toString());
		}
//		String chip=VocalConcertUtil.getChip();
//		UserChip userChip=userChipMapper.selectUserChipByUser(user_id);
//		if(chip.equals("chip_one")){
//			userChip.setChip_one(userChip.getChip_one()+1);
//			userChipMapper.updateChip(userChip);
//		}else if(chip.equals("chip_two")){
//			userChip.setChip_two(userChip.getChip_two()+1);
//			userChipMapper.updateChip(userChip);
//		}else if(chip.equals("chip_thr")){
//			userChip.setChip_thr(userChip.getChip_thr()+1);
//			userChipMapper.updateChip(userChip);
//		}else if(chip.equals("chip_for")){
//			userChip.setChip_for(userChip.getChip_for()+1);
//			userChipMapper.updateChip(userChip);
//		}else if(chip.equals("chip_fiv")){
//			userChip.setChip_fiv(userChip.getChip_fiv()+1);
//			userChipMapper.updateChip(userChip);
//		}
//		UserChipInfo userChipInfo=new UserChipInfo();
//		userChipInfo.setAdd_time(new Date());
//		userChipInfo.setUser_id(user_id);
//		userChipInfo.setChip(1);
//		userChipInfo.setStatus(1);
//		if(chip.equals("chip_one")){
//			userChipInfo.setChip(1);
//		}else if(chip.equals("chip_two")){
//			userChipInfo.setChip(2);
//		}else if(chip.equals("chip_thr")){
//			userChipInfo.setChip(3);
//		}else if(chip.equals("chip_for")){
//			userChipInfo.setChip(4);
//		}else if(chip.equals("chip_fiv")){
//			userChipInfo.setChip(5);
//		}
//		userChipInfo.setMessage("用户："+user_id+",任务获得"+chip);
//		userChipInfoMapper.insertUserChipInfo(userChipInfo);
		FrogAwards awards = new FrogAwards();
		total_balance.setScale(2, BigDecimal.ROUND_DOWN);
		awards.setAwards(total_balance.doubleValue());
		awards.setAdd_time(new Date());
		awards.setUser_id(user_id);
		awards.setTitle("蛙币—获取");
		awards.setMessage("完成任务奖励");
		// 送出蛙币
		frogUserAwardsMapper.insert(awards);
		logger.info("用户" + String.valueOf(user_id) + ",使用小程序完成任务，获得" + total_balance + "个蛙币");
		takeTaskInfoMapper.operateTakeTask((short)2, footStep, length, 2, take_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wa_coin", total_balance);
//		map.put("chip", chip);
//		map.put(arg0, arg1)
		FrogTask nowTask = taskInfoMapper.selectByPrimaryKey(task_id);
		if(nowTask.getAward_id() == null){
			System.out.println("当前任务任务点无奖励");
			map.put("hasaward", 0);
		}else{
			TaskAward taskAward = taskAwardmapper.getTaskAwardById(nowTask.getAward_id());
			if(taskAward!=null){
				//award   单个任务的奖励
//				if(random < taskAward.getProbability()&&taskAward.getAwardsurplus()>0){
//					map.put("hasaward", 1);
//					taskAward.setAwardsurplus(taskAward.getAwardsurplus()-1);
//					taskAwardmapper.updateTaskAwardById(taskAward);
//					map.put("award", taskAward);
//				}else{
					map.put("hasaward", 0);
				}
//			}
		}
		taskAwardmapper.getTaskAwardById(nowTask.getAward_id());
		if(user !=null &&activityTasks!=null){
			map.put("isactivitytask", 1);
//			if(lastTask.getTask_id()==activityTasks.get(user.getEventstep()).getTask_id()&&frogTake.getTask_id()==activityTasks.get(user.getEventstep()).getTask_id()){
//				//更新活动步骤 完成+1
//				if(user.getEventstep() == activityTasks.size()){
//					//完成任务
//					acUserMapper.updateActivityUserState(1, user.getUserid(), user.getActivityid());
//					map.put("activityaward", taskAwardmapper.getActivityAwardsByActivityId(user.getActivityid()));
//				}else{
//					acUserMapper.updateActivityUserStep(user.getEventstep()+1, user.getUserid(), user.getActivityid());
//					map.put("hasactivityaward", 0);
//				}
//			}else if(user.getEventstep()==0&&frogTake.getTask_id()==activityTasks.get(0).getTask_id()){
//				acUserMapper.updateActivityUserStep(1, user.getUserid(), user.getActivityid());
//				map.put("hasactivityaward", 0);
//			}		
//			if(user.getEventstep() == activityTasks.size()-1){
//				acUserMapper.updateActivityUserState(1, user.getUserid(), user.getActivityid());
//				map.put("activityaward", taskAwardmapper.getActivityAwardsByActivityId(user.getActivityid()));
//			}else{
				acUserMapper.updateActivityUserStep(user.getEventstep()+1, user.getUserid(), user.getActivityid());
				if((user.getEventstep()+1)==activityTasks.size()-1){					
//					TaskAward award = eventMapper.selectAwardByEnent(user.getNoweventid());
					FrogActivityAward award = getRandomAward(user.getActivityid());
					acUserMapper.updateActivityUserState(1, user.getUserid(), user.getActivityid());
					if(award!=null){
						map.put("hasactivityaward", 1);
						map.put("activityaward", award);
						logger.debug("获取到奖励");
						//获得奖品
						FrogActivityAwardHis his = new FrogActivityAwardHis();
						his.setActivity_id(user.getActivityid());
						his.setAward_id(award.getId());
						his.setGet_time(new Date());
						his.setAward_name(award.getAwardname());
						his.setAward_pic(award.getAwardpic());
						his.setState("0");	
						his.setUser_id(user_id);
						faahm.insertFrogActivityAwardHis(his);
						award.setAwardsurplus(award.getAwardsurplus()-1);
						awardMapper.updateAwards(award);
					}else{
						map.put("hasactivityaward", 0);
					}
				}else{
					map.put("hasactivityaward", 0);
				}
//			}
		}else{
			map.put("isactivitytask", 0);
			map.put("hasactivityaward", 0);
		}
//		if(user.getEventstep())
		apiResult.setResult(map);
		
		return apiResult;
	}

	public static void main(String[] args) throws InterruptedException {
		long a = System.currentTimeMillis();
		Thread.sleep(1000);
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	@Override
	public FrogTask getTask(Integer task_id) {
		return taskInfoMapper.selectTaskInfo(task_id);
	}

	@Override
	@Transactional(readOnly = false)
	public int addTask(String task_pic_id, Double qr_longitude, Double qr_latitude, Short task_type,
			String task_title, String task_info, String task_address,String award,String durtion) {
		// TODO Auto-generated method stub
		FrogTask task = new FrogTask();
		task.setTask_pic_id(task_pic_id);
		task.setLatitude(qr_latitude);
		task.setLongitude(qr_longitude);
		task.setTask_type(task_type);
		task.setTask_title(task_title);
		task.setTask_info(task_info);
		task.setAddress(task_address);
		task.setAdd_time(new Date());
		task.setAward(award);
		task.setTask_durtion(durtion);
		return taskInfoMapper.addTask(task);
	}

	@Override
	public int deleteTask(Integer task_id,Integer del) {
		// TODO Auto-generated method stub
		return taskInfoMapper.updateDel(task_id, del);
		
	}

	@Override
	public int updateTask(FrogTask task) {
		// TODO Auto-generated method stub
		return taskInfoMapper.updateTaskInfo(task);
	}

	@Override
	public List<FrogTask> getAllTasks(Integer page) {
		// TODO Auto-generated method stub
		return taskInfoMapper.selectAllTaskInfos(page);
	}
	
	private FrogActivityAward getRandomAward(Integer activityid){
		List<FrogActivityAward> awards = awardMapper.getAllAwards(activityid);
		FrogActivityAward nowAward = null;
		int totalnum = 0;
		for(FrogActivityAward award:awards){
			totalnum+=award.getAwardsurplus();
		}
		int random = (int)(Math.random()*totalnum)+1;
		logger.debug("获奖随机值："+random);
		System.out.println("获奖随机值："+random);
		int nownum = 0;
		for(FrogActivityAward award:awards){
			if(random>nownum && random<=(nownum+award.getAwardsurplus())){
				logger.debug("获奖随机值区间："+nownum+":"+(nownum+award.getAwardsurplus()));
				System.out.println("获奖随机值区间："+nownum+":"+(nownum+award.getAwardsurplus()));
				nowAward = award;
				break;
			}
			nownum+=award.getAwardsurplus();
		}
		return nowAward;
	}

}
