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
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.entity.Category;
import com.mycloud.entity.Quantity;
import com.mycloud.repository.CategoryRepository;
import com.mycloud.repository.QuantityRepository;
import com.mycloud.store.controller.BaseController;
import com.mycloud.util.PrimaryGenerater;

/**
 * @author Shawn
 *
 */
@Controller
public class DeliveryController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private QuantityRepository quantityRepository;
	
	@RequestMapping("/createDelivery.htm")
	public String createDelivery(Model model) {
		String deliveryNo = PrimaryGenerater.getInstance().generaterNextNumber();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(date);
		
		List<Category> categorys = categoryRepository.findAll();
		List<Quantity> quantitys = quantityRepository.findAll();
		
		
		model.addAttribute("categorys", categorys);
		model.addAttribute("quantitys", quantitys);
		model.addAttribute("deliveryNo", deliveryNo);
		model.addAttribute("today", today);
		return "/context/delivery/deliveryForm";
	}
	
	@RequestMapping("/searchDelivery.htm")
	public String searchDelivery(Model model) {
		model.addAttribute("rants", "hello,world");
		return "/context/delivery/deliveryList";
	}

}
