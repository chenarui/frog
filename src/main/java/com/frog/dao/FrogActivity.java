package com.frog.dao;

import java.util.List;

import com.frog.model.FrogActivityBean;

public interface FrogActivity {
	FrogActivityBean getNowFrogActivity(Integer isdel);
	List<FrogActivityBean> getAllFrogActivity();
    void delFrogActivityById(Integer id);
    void updateFrogActivity(FrogActivityBean bean);
    FrogActivityBean getFrogActivityById(Integer id);
    void changeFrogActivityState(Integer state,Integer id);
}
