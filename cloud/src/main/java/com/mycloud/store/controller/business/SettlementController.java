/**
 * 
 */
package com.mycloud.store.controller.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.repository.CategoryRepository;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.ExpressForm;
import com.mycloud.store.service.ExpressService;
import com.mycloud.util.PrimaryGenerater;

/**
 * @author Shawn
 *
 */
@Controller
public class SettlementController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(SettlementController.class);
	
	@Autowired
	private ExpressService expressService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping("/createSettlement.htm")
	public String createSettlement(Model model) {
		String settlementNo = PrimaryGenerater.getInstance().generaterNextNumber();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(date);

		model.addAttribute("settlementNo", settlementNo);
		model.addAttribute("today", today);
		return "/context/settlement/settlementForm";
	}
	
	
	@RequestMapping("/submitSettlement.htm")
	public String submitSettlement(@ModelAttribute ExpressForm expressForm, Model model) {
		Customer customer = getCustomer();
		expressService.saveExpress(customer.getId(), expressForm);
		return "forward:createSettlement.htm";
	}
	
	@RequestMapping("/searchSettlement.htm")
	public String searchExpress(Model model) {
		List<Category> categorys = categoryRepository.findAll();
		model.addAttribute("categorys", categorys);
		return "/context/settlement/settlementList";
	}

	
	

}