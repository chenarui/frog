package com.frog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.frog.dao.FrogActivityEventMapper;
import com.frog.model.FrogActivityEvent;
import com.frog.model.FrogTask;
import com.frog.model.TaskAward;
import com.frog.service.FrogActivityEventService;

public class FrogActivityEventServiceImpl implements FrogActivityEventService{

	@Autowired
	private FrogActivityEventMapper mapper;
	@Override
	public List<FrogActivityEvent> getAllEvents() {
		// TODO Auto-generated method stub
		return mapper.selectAllActivityEvents();
	}
	@Override
	public TaskAward getAwardByEventId(Integer eventId) {
		// TODO Auto-generated method stub
		return mapper.selectAwardByEnent(eventId);
	}
	@Override
	public FrogActivityEvent getEventById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectFrogActivityEventById(id);
	}
	
	@Override
	public List<FrogActivityEvent> getEventByDiff(Integer diff) {
		// TODO Auto-generated method stub
		return mapper.selectEventByDiff(diff);
	}
	@Override
	public List<FrogActivityEvent> getEventByDiffTheme(Integer diff, Integer theme) {
		// TODO Auto-generated method stub
		return mapper.selectEventByDiffTheme(diff, theme);
	}
	@Override
	public int addEvent(String eventname, String diff, String theme, String event_ref, 
			String additional, String event_wire, Double event_price ,String address,String event_pic) {
		// TODO Auto-generated method stub
		FrogActivityEvent event = new FrogActivityEvent();
		event.setEventname(eventname);
		event.setDiff(diff);
		event.setTheme(theme);
		event.setEvent_ref(event_ref);
		event.setAdditional(additional);
		event.setEvent_price(event_price);
		event.setAddress(address);
		event.setEvent_wire(event_wire);
		event.setEvent_pic(event_pic);
		
		return mapper.addFrogActivityEvent(event);
	}
	@Override
	public int addEventPic(FrogActivityEvent event) {
		// TODO Auto-generated method stub
		
		return mapper.addEventPic(event);
	}
	@Override
	public List<FrogActivityEvent> getEvents(FrogActivityEvent event) {
		// TODO Auto-generated method stub
		return mapper.selectEvents(event);
	}
	
	
	
	
	
	
	
}
