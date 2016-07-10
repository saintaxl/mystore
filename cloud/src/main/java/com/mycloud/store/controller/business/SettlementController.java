/**
 * 
 */
package com.mycloud.store.controller.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.constant.ExpressStatus;
import com.mycloud.constant.SettlementStatus;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Express;
import com.mycloud.entity.MonthlyStatement;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.SettlementForm;
import com.mycloud.store.service.ExpressService;
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
	
	@Autowired
	private ExpressService expressService;

	@RequestMapping("/createSettlement.htm")
	public String createSettlement(Model model) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(date);
		
		Calendar rightNow = Calendar.getInstance();
		Integer rightMonth  = rightNow.get(Calendar.MONTH) + 1;
		
		Date thisMonthsFirstDay = DateUtils.setDays(new Date(), 1);
		Date thisMonths25th = DateUtils.setDays(new Date(), 25);
		Date nextMonthsFirstDay = DateUtils.addMonths(thisMonthsFirstDay, 1);
		Date thisMonthsLastDay = DateUtils.addDays(nextMonthsFirstDay, -1);
		
		Customer customer = getCustomer();
		
		MonthlyStatement monthly = settlementService.getMonthlyStatementByMonth(customer.getId(),rightMonth.toString(), SettlementStatus.PENDING);
		BigDecimal totalPrice = new BigDecimal(0);

		if(monthly!=null){
			List<Express> expressList = expressService.getExpressList(customer.getId(),thisMonthsFirstDay,thisMonthsLastDay,ExpressStatus.RECEIVED);
			for (Express express : expressList) {
				BigDecimal price = express.getLogistics().getPrice();
				totalPrice = totalPrice.add(price).setScale(2, RoundingMode.HALF_UP);
			}
		}
		
		model.addAttribute("rightMonth", rightMonth);
		model.addAttribute("totalLogisticsPrice", totalPrice);
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
	
	@RequestMapping("/searchSettlement.htm")
	public String searchSettlement(Model model) {
		Calendar rightNow = Calendar.getInstance();
		Integer rightMonth  = rightNow.get(Calendar.MONTH) + 1;
		
		SettlementStatus[] status = SettlementStatus.values();
		
		model.addAttribute("status", status);
		model.addAttribute("rightMonth", rightMonth);
		return "/context/settlement/settlementList";
	}
	
	public static void main(String[] args) {
		Date currectMonths = DateUtils.setMonths(new Date(), Integer.valueOf(7-1));
		Date thisMonths25th = DateUtils.setDays(currectMonths, 25);
		Date lastMonths25th = DateUtils.addMonths(thisMonths25th, -1);
		System.out.println(currectMonths);
		System.out.println(thisMonths25th);
		System.out.println(lastMonths25th);
    }
	
	

}
