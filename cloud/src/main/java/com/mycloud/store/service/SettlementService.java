package com.mycloud.store.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.constant.SettlementStatus;
import com.mycloud.entity.Customer;
import com.mycloud.entity.DailySettlement;
import com.mycloud.repository.CustomerRepository;
import com.mycloud.repository.DailySettlementRepository;
import com.mycloud.repository.InventoryRepository;

@Service
@Transactional
public class SettlementService{
	
	private final static Logger logger = LoggerFactory.getLogger(SettlementService.class);
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DailySettlementRepository dailySettlementRepository;
	
	public void dailySettlement(){
		logger.info("Run daily settlement ....");
		List<Object[]> dailyList = inventoryRepository.findSumVolumeGroupByCustomerId();
		for (Object[] object : dailyList) {
			Integer customerId = (Integer)object[3];
			Date yesterday = DateUtils.addDays(new Date(), -1);
			Customer customer = customerRepository.findOne(customerId);
			
			logger.info("Get customer:{} by daily settlement ....", customer.getId());
			
			List<DailySettlement> findBySettlementDate = dailySettlementRepository.findBySettlementDateAndCustomer(yesterday,customer);
			if(CollectionUtils.isNotEmpty(findBySettlementDate)){
				continue;
			}
			
			DailySettlement dailySettlement = new DailySettlement();
			dailySettlement.setCustomer(customer);
			dailySettlement.setPrice(new BigDecimal((Double)object[1]).multiply(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP));
			dailySettlement.setStatus(SettlementStatus.UNPAYED);
			dailySettlement.setTotalNumber(((Long)object[2]).intValue());
			dailySettlement.setTotalVolume((Double)object[1]);
			dailySettlement.setTotalWeight((Double)object[0]);
			dailySettlement.setSettlementDate(yesterday);
			dailySettlementRepository.saveAndFlush(dailySettlement);
			
			logger.info("Save entity dailySettlement[{}] by customer:{} ",dailySettlement.getId(), customer.getId());
        }
		logger.info("End daily settlement ....");
	}

}
