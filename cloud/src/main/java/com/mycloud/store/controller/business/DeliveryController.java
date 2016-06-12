/**
 * 
 */
package com.mycloud.store.controller.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Logistics;
import com.mycloud.entity.Quantity;
import com.mycloud.repository.CategoryRepository;
import com.mycloud.repository.QuantityRepository;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.service.DeliveryService;
import com.mycloud.store.service.LogisticsService;
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
	
	@Autowired
	private LogisticsService logisticsService;
	
	@Autowired
	private DeliveryService deliveryService;
	
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
	
	@RequestMapping("/submitDelivery.htm")
	public String submitDelivery(@RequestParam(value = "deliveryNo", required = true) String deliveryNo,
			@RequestParam(value="productName", required = true) List<String> productName, 
			@RequestParam(value="barCodeInput", required = true) List<String> barCode, 
			@RequestParam(value="category", required = true) List<Integer> category, 
			@RequestParam(value="color", required = false) List<String> color, 
			@RequestParam(value="number", required = true) List<Integer> number, 
			@RequestParam(value="quantity", required = false) List<Integer> quantity, 
			@RequestParam(value="volume", required = false) List<Double> volume, 
			@RequestParam(value="weight", required = false) List<Double> weight, 
			@RequestParam(value="note", required = false) List<String> note, 
			@RequestParam(value="logisticsCompanyName", required = true) String logisticsCompanyName, 
			@RequestParam(value="logisticsCompanyNameText", required = false) String logisticsCompanyNameText, 
			@RequestParam(value="logisticsNo", required = true) String logisticsNo, 
			@RequestParam(value="logisticsDate", required = true)  @DateTimeFormat(pattern="yyyy-MM-dd") Date logisticsDate, 
			Model model){
		
		String companyName = logisticsCompanyName;
		if(StringUtils.isNotEmpty(logisticsCompanyNameText)){
			companyName = logisticsCompanyNameText;
		}
		
		Logistics logistics = logisticsService.addLogistics(logisticsDate, companyName, logisticsNo);
		
		Customer customer = getCustomer();
		
		for (int i = 0; i < productName.size(); i++) {
			String barCodeStr = StringUtils.isNotEmpty(barCode.get(i)) ? barCode.get(i) : "" ;
			Integer categoryId = category.get(i);
			Integer quantityId = quantity.get(i);
			String colorStr = StringUtils.isNotEmpty(color.get(i)) ? color.get(i) : "" ;
			String noteStr = StringUtils.isNotEmpty(note.get(i)) ? note.get(i) : "" ;
			Integer numberParm = number.get(i)!=null ? number.get(i) : 0;
			String productNameStr = StringUtils.isNotEmpty(productName.get(i)) ? productName.get(i) : "" ;
			Double volumeParm = volume.get(i)!=null ? volume.get(i) : 0;
			Double weightParm = weight.get(i)!=null ? weight.get(i) : 0;
			deliveryService.addDelivery(barCodeStr, categoryId, quantityId, colorStr, customer, deliveryNo, noteStr, numberParm, productNameStr, volumeParm, weightParm, logistics);
        }
		
		
		return "forward:createDelivery.htm";
	}
	
	@RequestMapping("/searchDelivery.htm")
	public String searchDelivery(Model model) {
		model.addAttribute("rants", "hello,world");
		return "/context/delivery/deliveryList";
	}

}
