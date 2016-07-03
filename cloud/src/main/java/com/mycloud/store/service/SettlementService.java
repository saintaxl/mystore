package com.mycloud.store.service;

import org.springframework.stereotype.Service;

@Service
public class SettlementService {
	
	public void dailySettlement(){
		System.out.println(Thread.currentThread().getName());
	}

}
