/**
 * 
 */
package com.mycloud.store.controller.business;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.constant.SettlementStatus;
import com.mycloud.entity.Customer;
import com.mycloud.entity.MonthlyStatement;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.SettlementForm;
import com.mycloud.store.service.SettlementService;

/**
 * @author Shawn
 *
 */
@Controller
public class SettlementController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(SettlementController.class);
	

	@Autowired
	private SettlementService settlementService;

	@RequestMapping("/createSettlement.htm")
	public String createSettlement(Model model) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(date);
		
		Calendar rightNow = Calendar.getInstance();
		Integer rightMonth  = rightNow.get(Calendar.MONTH) + 1;
		
		Date thisMonthsFirstDay = DateUtils.setDays(new Date(), 1);
		Date thisMonths25th = DateUtils.setDays(new Date(), 25);
		
		Customer customer = getCustomer();
		
		MonthlyStatement monthly = settlementService.getMonthlyStatementByMonth(customer.getId(),rightMonth.toString(), SettlementStatus.PENDING);
		
		model.addAttribute("rightMonth", rightMonth);
		model.addAttribute("from", formatter.format(thisMonthsFirstDay));
		model.addAttribute("to", formatter.format(thisMonths25th));
		if(monthly!=null){
			model.addAttribute("settlementNo", monthly.getSettlementNo());
		}
		model.addAttribute("today", today);
		return "/context/settlement/settlementForm";
	}
	
	
	@RequestMapping("/submitSettlement.htm")
	public String submitSettlement(@ModelAttribute SettlementForm settlementForm, Model model) {
		Customer customer = getCustomer();
		settlementService.updateMonthlyStatement(customer.getId(), settlementForm);
		return "forward:createSettlement.htm";
	}
	
	

}
