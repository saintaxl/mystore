package com.mycloud.store.service;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.constant.ExpressStatus;
import com.mycloud.constant.LogisticsType;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Express;
import com.mycloud.entity.ExpressDetails;
import com.mycloud.entity.Inventory;
import com.mycloud.entity.Logistics;
import com.mycloud.exception.BusinessException;
import com.mycloud.repository.ExpressDetailsRepository;
import com.mycloud.repository.ExpressRepository;
import com.mycloud.repository.InventoryRepository;
import com.mycloud.store.controller.form.ExpressForm;
import com.mycloud.store.exception.ErrorCode;

@Service
@Transactional
public class ExpressService {
	
	private Logger logger = LoggerFactory.getLogger(ExpressService.class);
	
	@Autowired
	private ExpressRepository expressRepository;

	@Autowired
	private ExpressDetailsRepository expressDetailsRepository;

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private LogisticsService logisticsService;

	public void saveExpress(Customer customer, ExpressForm expressForm){
		
		Logistics logistics = new Logistics();
		logistics.setCompanyName(expressForm.getLogisticsCompanyName());
		logistics.setLogisticsNo(expressForm.getLogisticsNo());
		logistics.setLogisticsType(LogisticsType.Express);
		logistics.setAddress(expressForm.getAddress());
		logistics.setProvince(expressForm.getProvince());
		logistics.setCity(expressForm.getCity());
		logistics.setDistrict(expressForm.getDistrict());
		logistics.setInsurance(expressForm.getInsurance());
		logistics.setMobile(expressForm.getMobile());
		logistics.setName(expressForm.getName());
		logistics.setNeedPackage(expressForm.getNeedPackage());
		logistics.setPrice(expressForm.getPrice());
		logisticsService.saveLogistics(logistics);
		
		Express express = new Express();
		express.setCustomer(customer);
		express.setExpressNo(expressForm.getExpressNo());
		express.setLogistics(logistics);
		express.setStatus(ExpressStatus.SHIPPED);
		expressRepository.save(express);
		
		for (int i = 0; i < expressForm.getNumber().size(); i++) {
			Integer inventoryId =  CollectionUtils.isNotEmpty(expressForm.getInventoryId()) && expressForm.getInventoryId().get(i) != null ? expressForm.getInventoryId().get(i) : 0;
			Integer number =  CollectionUtils.isNotEmpty(expressForm.getNumber()) && expressForm.getNumber().get(i) != null ? expressForm.getNumber().get(i) : 0;
			if(number <= 0){
				continue;
			}
			Inventory inventory = inventoryRepository.findOne(inventoryId);
			Integer currentNumber = inventory.getNumber() - number;
			if(currentNumber < 0){
				logger.error("Not enough inventory, productName:{}", inventory.getProductName());
				throw new BusinessException(ErrorCode.NOT_ENOUGH_INVENTORY, inventory.getProductName());
			}
			inventory.setNumber(currentNumber);
			inventoryRepository.save(inventory);
			
			ExpressDetails expressDetails = new ExpressDetails();
			expressDetails.setCustomer(customer);
			expressDetails.setExpress(express);
			expressDetails.setInventory(inventory);
			expressDetails.setNumber(number);
			
			expressDetailsRepository.save(expressDetails);
        }
		
		
	}

	



}
