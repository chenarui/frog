package com.frog.TransactionManager;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionTemplate;

import com.frog.service.Service;

public class ServiceImpl implements Service {
	  private final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);  
	  @Resource  
	   private TransactionTemplate transactionTemplate;
	

	
}
