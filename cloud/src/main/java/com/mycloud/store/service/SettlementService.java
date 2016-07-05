package com.mycloud.store.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
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
import com.mycloud.entity.DailySettlementDetails;
import com.mycloud.entity.Inventory;
import com.mycloud.entity.MonthlyStatement;
import com.mycloud.repository.CustomerRepository;
import com.mycloud.repository.DailySettlementDetailsRepository;
import com.mycloud.repository.DailySettlementRepository;
import com.mycloud.repository.InventoryRepository;
import com.mycloud.repository.MonthlyStatementRepository;

@Service
@Transactional
public class SettlementService {

	private final static Logger logger = LoggerFactory.getLogger(SettlementService.class);

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private DailySettlementRepository dailySettlementRepository;

	@Autowired
	private DailySettlementDetailsRepository dailySettlementDetailsRepository;
	
	@Autowired
	private MonthlyStatementRepository monthlyStatementRepository;

	public void dailySettlement() {
		logger.info("Run daily settlement ....");
		List<Object[]> dailyList = inventoryRepository.findSumVolumeGroupByCustomerId();
		for (Object[] object : dailyList) {
			Integer customerId = (Integer) object[3];
			Date yesterday = DateUtils.addDays(new Date(), -1);
			Customer customer = customerRepository.findOne(customerId);

			logger.info("Get customer:{} by daily settlement ....", customer.getId());

			List<DailySettlement> findBySettlementDate = dailySettlementRepository.findBySettlementDateAndCustomer(yesterday, customer);
			if (CollectionUtils.isNotEmpty(findBySettlementDate)) {
				continue;
			}

			DailySettlement dailySettlement = new DailySettlement();
			dailySettlement.setCustomer(customer);
			dailySettlement.setPrice(new BigDecimal((Double) object[1]).multiply(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP));
			dailySettlement.setStatus(SettlementStatus.PENDING);
			dailySettlement.setTotalNumber(((Long) object[2]).intValue());
			dailySettlement.setTotalVolume((Double) object[1]);
			dailySettlement.setTotalWeight((Double) object[0]);
			dailySettlement.setSettlementDate(yesterday);
			dailySettlementRepository.saveAndFlush(dailySettlement);

			logger.info("Save entity dailySettlement[{}] by customer:{} ", dailySettlement.getId(), customer.getId());

			List<DailySettlementDetails> settlementDetails = dailySettlementDetailsRepository.findBySettlementDateAndCustomer(yesterday, customer);
			if (CollectionUtils.isNotEmpty(settlementDetails)) {
				continue;
			}

			List<Inventory> inventorys = inventoryRepository.findByCustomerAndNumberGreaterThan(customer, 0);
			for (Inventory inventory : inventorys) {
				DailySettlementDetails dailySettlementDetails = new DailySettlementDetails();
				dailySettlementDetails.setDailySettlement(dailySettlement);
				dailySettlementDetails.setBarCode(inventory.getBarCode());
				dailySettlementDetails.setCategory(inventory.getCategory());
				dailySettlementDetails.setColor(inventory.getColor());
				dailySettlementDetails.setCustomer(customer);
				dailySettlementDetails.setNumber(inventory.getNumber());
				dailySettlementDetails.setProductName(inventory.getProductName());
				dailySettlementDetails.setQuantity(inventory.getQuantity());
				dailySettlementDetails.setSettlementDate(yesterday);
				dailySettlementDetails.setShelvesNo(inventory.getShelvesNo());
				dailySettlementDetails.setVolume(inventory.getVolume());
				dailySettlementDetails.setWeight(inventory.getWeight());
				dailySettlementDetailsRepository.save(dailySettlementDetails);
			}

		}
		logger.info("End daily settlement ....");
	}

	public void monthlySettlement() {
		logger.info("Run monthly settlement ....");
		Date thisMonthsFirstDay = DateUtils.setDays(new Date(), 1);
		Date nextMonthsFirstDay = DateUtils.addMonths(thisMonthsFirstDay, 1);
		Date thisMonthsLastDay = DateUtils.addDays(nextMonthsFirstDay, -1);
		Calendar cal = Calendar.getInstance();
		int thisMonths = cal.get(Calendar.MONTH )+1;
		String thisMonthsStr = new Integer(thisMonths).toString();
		
		List<Customer> customer = customerRepository.findAll();
		for (Customer cust : customer) {
			List<MonthlyStatement> monthlyStatements = monthlyStatementRepository.findByMonthAndCustomerAndStatus(thisMonthsStr,cust,SettlementStatus.PENDING);
			if (CollectionUtils.isNotEmpty(monthlyStatements)) {
				continue;
			}
			
			List<DailySettlement> dailySettlements = dailySettlementRepository.findBySettlementDateAfterAndSettlementDateBeforeAndStatusAndCustomer(thisMonthsFirstDay,thisMonthsLastDay,SettlementStatus.PENDING,cust);
			if (CollectionUtils.isEmpty(dailySettlements)) {
				continue;
			}
			
			MonthlyStatement monthlyStatement = new MonthlyStatement();
			monthlyStatement.setAmount(new BigDecimal(0));
			monthlyStatement.setCustomer(cust);
			monthlyStatement.setMonth(thisMonthsStr);
			monthlyStatement.setStatus(SettlementStatus.PENDING);
			
			MonthlyStatement afterMonthlyStatement = monthlyStatementRepository.save(monthlyStatement);
			BigDecimal totalAmount = new BigDecimal(0);
			for (DailySettlement dailySettlement : dailySettlements) {
				dailySettlement.setStatus(SettlementStatus.PROCESSING);
				dailySettlement.setMonthlyStatement(afterMonthlyStatement);
				dailySettlementRepository.save(dailySettlement);
				
				totalAmount = totalAmount.add(dailySettlement.getPrice()).setScale(2, RoundingMode.HALF_UP);
            }
			
			afterMonthlyStatement.setAmount(totalAmount);
			monthlyStatementRepository.save(afterMonthlyStatement);
        }
		
		logger.info("end monthly settlement ....");
	}

	public static void main(String[] args) {
		Date thisMonthsFirstDay = DateUtils.setDays(new Date(), 1);
		Date nextMonthsFirstDay = DateUtils.addMonths(thisMonthsFirstDay, 1);
		Date thisMonthsLastDay = DateUtils.addDays(nextMonthsFirstDay, -1);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		System.out.println(thisMonthsFirstDay);
		System.out.println(nextMonthsFirstDay);
		System.out.println(thisMonthsLastDay);
		System.out.println(month);
		
	}

}
