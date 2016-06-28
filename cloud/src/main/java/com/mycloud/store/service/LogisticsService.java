/**
 * 
 */
package com.mycloud.store.service;

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
	
	public Logistics saveLogistics(Logistics logistics){
		logisticsRepository.save(logistics);
		return logistics;
	}

}
