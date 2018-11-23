package com.frog.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frog.model.UserAddress;

public interface UserAddressMapper {
	
	public int insertAddress(@Param("user_id")Integer user_id,@Param("name")String name,@Param("phone")String phone,@Param("address")String address,@Param("create_date")Date create_date );

	public List<UserAddress> selectAddressfromUser (@Param("user_id")Integer user_id);
	
}
