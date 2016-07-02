package com.mycloud.store.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.constant.ExpressStatus;
import com.mycloud.constant.LogisticsType;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Express;
import com.mycloud.entity.ExpressDetails;
import com.mycloud.entity.Inventory;
import com.mycloud.entity.Logistics;
import com.mycloud.entity.LogisticsCompany;
import com.mycloud.exception.BusinessException;
import com.mycloud.repository.CustomerRepository;
import com.mycloud.repository.ExpressDetailsRepository;
import com.mycloud.repository.ExpressRepository;
import com.mycloud.repository.InventoryRepository;
import com.mycloud.repository.LogisticsCompanyRepository;
import com.mycloud.store.controller.form.ExpressForm;
import com.mycloud.store.controller.form.ExpressListForm;
import com.mycloud.store.controller.rest.model.ExpressDetailRequest;
import com.mycloud.store.controller.rest.model.ExpressRequest;
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
	private CustomerRepository customerRepository;

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private LogisticsCompanyRepository logisticsCompanyRepository;
	
	@Autowired
	private LogisticsService logisticsService;

	public void saveExpress(Integer customerId, ExpressForm expressForm){
		
		Logistics logistics = new Logistics();
		LogisticsCompany logisticsCompany = logisticsCompanyRepository.findOne(expressForm.getLogisticsCompany());
		logistics.setCompanyName(logisticsCompany.getCompanyName());
		logistics.setLogisticsCompany(logisticsCompany);
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
		
		Customer customer = customerRepository.findOne(customerId);

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

	public Page<ExpressDetails> searchDetails(final ExpressListForm expressListForm, final Customer customer, Pageable pageable) {

		Specification<ExpressDetails> spec = new Specification<ExpressDetails>() {
			public Predicate toPredicate(Root<ExpressDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("customer").get("id").as(Integer.class), customer.getId() ));
				list.add(cb.equal(root.get("express").get("logistics").get("logisticsType").as(LogisticsType.class), LogisticsType.Express ));
				
				if (StringUtils.isNotEmpty(expressListForm.getBarCode())) {
					list.add(cb.like(root.get("inventory").get("barCode").as(String.class), "%" + expressListForm.getBarCode() + "%"));
				}
				if (StringUtils.isNotEmpty(expressListForm.getExpressNo())) {
					list.add(cb.equal(root.get("express").get("expressNo").as(String.class),  expressListForm.getExpressNo() ));
				}
				if (StringUtils.isNotEmpty(expressListForm.getProductName())) {
					list.add(cb.like(root.get("inventory").get("productName").as(String.class), "%" + expressListForm.getProductName() + "%"));
				}
				if (StringUtils.isNotEmpty(expressListForm.getLogisticsCompanyName())) {
					list.add(cb.like(root.get("express").get("logistics").get("companyName").as(String.class), "%" + expressListForm.getLogisticsCompanyName() + "%"));
				}
				if (StringUtils.isNotEmpty(expressListForm.getLogisticsNo())) {
					list.add(cb.equal(root.get("express").get("logistics").get("logisticsNo").as(String.class),  expressListForm.getLogisticsNo() ));
				}
				if (expressListForm.getCategory()!=null) {
					list.add(cb.equal(root.get("inventory").get("category").get("id").as(Integer.class), expressListForm.getCategory() ));
				}
				if(StringUtils.isNotEmpty(expressListForm.getMobile())){
					list.add(cb.equal(root.get("express").get("logistics").get("mobile").as(String.class), expressListForm.getMobile() ));
				}
				if(StringUtils.isNotEmpty(expressListForm.getName())){
					list.add(cb.equal(root.get("express").get("logistics").get("name").as(String.class), expressListForm.getName() ));
				}
				
			
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		
		Page<ExpressDetails> findAll = expressDetailsRepository.findAll(spec, pageable);
		return findAll;
    }
	

	public Page<Express> searchExpress(final ExpressListForm expressListForm, final Customer customer, Pageable pageable) {
		Specification<Express> spec = new Specification<Express>() {
			public Predicate toPredicate(Root<Express> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("customer").get("id").as(Integer.class), customer.getId() ));
				
				if(expressListForm.getStatus()!=null){
					list.add(cb.equal(root.get("status").as(ExpressStatus.class), expressListForm.getStatus() ));
				}
				
			
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		
		Page<Express> findAll = expressRepository.findAll(spec, pageable);
		return findAll;
    }

	public BigDecimal calculatePrice(ExpressRequest expressRequest) {
		List<ExpressDetailRequest> details = expressRequest.getDetails();
		Integer logisticsCompanyId = expressRequest.getLogisticsCompanyId();
		LogisticsCompany logisticsCompany = logisticsCompanyRepository.findOne(logisticsCompanyId);
		BigDecimal unitPrice = logisticsCompany.getUnitPrice();
		
		BigDecimal totalPrice = new BigDecimal(0);
		for (ExpressDetailRequest expressDetailRequest : details) {
			Inventory inventory = inventoryRepository.findOne(expressDetailRequest.getId());
			Double weight = inventory.getWeight();
			Integer number = expressDetailRequest.getNumber();
			DecimalFormat df = new DecimalFormat("#.##");  
			Double totalWeight = weight*number;
			totalWeight =  Double.parseDouble(df.format(totalWeight));  
			
			BigDecimal singleaprice = unitPrice.multiply(new BigDecimal(totalWeight)) ;
			singleaprice = singleaprice.setScale(2, BigDecimal.ROUND_HALF_UP);
			totalPrice = totalPrice.add(singleaprice);
        }
		
		return totalPrice;
    }



}
