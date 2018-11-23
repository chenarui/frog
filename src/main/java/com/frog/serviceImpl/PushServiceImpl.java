package com.frog.serviceImpl;

import java.util.HashMap;

import com.frog.push.Demo;
import com.frog.push.PushData;
import com.frog.service.PushService;

public class PushServiceImpl implements PushService{
	
	@Override
	public void sendAndroidBrocast(String ticker, String title, String text, boolean isDebug,
			HashMap<String, String> extraData) {
		// TODO Auto-generated method stub
		Demo demo = new Demo(PushData.ANDROID_APPKEY, PushData.ANDROID_MASTERSECRET);
		try {
			demo.sendAndroidBroadcast(ticker, title, text, isDebug, extraData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("推送失败");
		}
	}

	@Override
	public void sendIOSBrocast(String alert) {
		// TODO Auto-generated method stub
		Demo demo = new Demo(PushData.IOS_APPKEY, PushData.IOS_MASTERSECRET);
		try {
			demo.sendIOSBroadcast("text");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("推送失败");
		}
	}

}
