package com.frog.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.dubbo.config.support.Parameter;
import com.frog.model.UserInfo;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

	public UserInfo getUserInfoByPhone(@Param("phone") String phone);

	public int insert(UserInfo info);

	public UserInfo selectUserInfoByOpenId(@Param("open_id") String open_id);
	public UserInfo selectUserInfoByUnionId(@Param("union_id") String union_id);
	
	public int updateBalance(@Param("balance")BigDecimal balance,@Param("user_id")Integer user_id);
	
	public UserInfo selectUserByUserId(@Param("user_id")Integer user_id);
	
	public int updateUserInfoByUserId(@Param("phone")String phone,@Param("user_id")Integer user_id);

	public int updateUserInfo(@Param("next_qr_code")String next_qr_code,@Param("user_id")Integer user_id);

    public UserInfo getUserInfoByUserId(@Param("user_id")Integer user_id);
    
    public int updateTotalBalance(@Param("total_balance")BigDecimal total_balance,@Param("user_id")Integer user_id);
    
    public UserInfo selectUserInfoByInviteId(@Param("invite_id")Integer invite_id);
    
    public int updateInviteID(@Param("invite_id")Integer invite_id,@Param("user_id")Integer user_id);
    
    public int updateLoginType(@Param("first_login")Integer first_login,@Param("user_id")Integer user_id);
    
    public int updateUserNickname(@Param("nickName")String nickName,@Param("user_id")Integer user_id);
    public int updateUserPhone(@Param("phone")String phone,@Param("user_id")Integer user_id);
    
}
