package com.frog.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.frog.dao.QrMapper;
import com.frog.dao.TakeTaskInfoMapper;
import com.frog.dao.TaskInfoMapper;
import com.frog.dao.UserInfoMapper;
import com.frog.model.FrogRQ;
import com.frog.model.FrogTake;
import com.frog.model.FrogTask;
import com.frog.service.TaskTakeService;

@Transactional(readOnly = true)
public class TaskTakeServiceImpl implements TaskTakeService {
	@Autowired
	private TakeTaskInfoMapper takeTaskInfoMapper;
	@Autowired
	private TaskInfoMapper taskInfoMapper;
	@Autowired
	private QrMapper qrMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	@Transactional(readOnly = false)
	public int save(Integer user_id, Integer task_id  ,String task_info,String address,String task_title,Double QR_longitude,Double QR_latitude,Double task_price,Double hea_price,Double total_price,Integer type,Integer last_taskid,String qr_code_id,Double distance) {
		FrogTake frogTake = new FrogTake();
		frogTake.setUser_id(user_id);
		frogTake.setTask_id(task_id);
		frogTake.setAdd_time(new Date());
		frogTake.setStatus((short) 0);
		frogTake.setTask_name(task_title);
		frogTake.setTask_info(task_info);
		frogTake.setQr_code_id(qr_code_id);
		frogTake.setDistance(distance);
		FrogTask frogTask=taskInfoMapper.selectTaskInfo(task_id);
		frogTake.setStart_latitude(QR_latitude);
		frogTake.setStart_longitude(QR_longitude);
		if(frogTask == null){
			System.out.println("task不存在"+task_id);
			return -1;
		}
		frogTake.setFinal_longitude(frogTask.getLongitude());
		frogTake.setFinal_latitude(frogTask.getLatitude());
		frogTake.setTask_price(task_price);
		frogTake.setType(type);
		frogTake.setHea_price(hea_price);
		frogTake.setTotal_price(total_price);
		frogTake.setLast_taskid(last_taskid);
		//同时根据目的地寻找下一个码的位置
		System.out.println("qrcodeaddress:"+address);
		FrogRQ frogRQ=qrMapper.selectQrInfoByQrCodeAddress(address.trim(), false);
		if (frogRQ == null || frogRQ.getQr_code_id()==null) {
			System.out.println("something fail");
			return -1;
		}
		userInfoMapper.updateUserInfo(frogRQ.getQr_code_id(), user_id);
		takeTaskInfoMapper.insert(frogTake);
		System.out.println(frogTake.getTake_id());
		return frogTake.getTake_id();
	}

	@Override
	public int takeTaskCount(Integer user_id, Integer status) {
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("status", status);
		return takeTaskInfoMapper.selectNoCompleteTask(map);
	}


	@Override
	public FrogTake getTakeInfo(Integer take_id) {
		return takeTaskInfoMapper.selectTakeByTakeId(take_id);
	}

	@Override
	public List<FrogTake> getAllTakes(Integer user_id, short status, Integer page) {
		// TODO Auto-generated method stub
		return takeTaskInfoMapper.selectAllMyTask(user_id, status, page);
	}
  
}
