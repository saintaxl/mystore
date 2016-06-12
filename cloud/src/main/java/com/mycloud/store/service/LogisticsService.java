/**
 * 
 */
package com.mycloud.store.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.entity.Logistics;
import com.mycloud.repository.LogisticsRepository;

/**
 * @author Shawn
 *
 */

@Service
@Transactional
public class LogisticsService {
	
	@Autowired
	private LogisticsRepository logisticsRepository;
	
	public Logistics addLogistics(Date arrivalDate, String companyName,String logisticsNo){
		Logistics logistics = new Logistics();
		logistics.setArrivalDate(arrivalDate);
		logistics.setCompanyName(companyName);
		logistics.setLogisticsNo(logisticsNo);
		
		logisticsRepository.save(logistics);
		return logistics;
	}

}
