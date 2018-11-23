package com.frog.dao;

import org.apache.ibatis.annotations.Param;

import com.frog.model.FrogRQ;

public interface QrMapper {

	FrogRQ selectQRInfo(@Param("qr_code_id")String qr_code_id,@Param("del")boolean del);
	FrogRQ selectQrInfoByQrCodeAddress(@Param("qr_code_address")String qr_code_address,@Param("del")Boolean del);
}
