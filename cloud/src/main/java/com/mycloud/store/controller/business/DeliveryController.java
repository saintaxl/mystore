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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Quantity;
import com.mycloud.repository.CategoryRepository;
import com.mycloud.repository.QuantityRepository;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.DeliveryForm;
import com.mycloud.store.service.DeliveryService;
import com.mycloud.util.PrimaryGenerater;

/**
 * @author Shawn
 *
 */
@Controller
public class DeliveryController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private QuantityRepository quantityRepository;

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
	public String submitDelivery(@ModelAttribute DeliveryForm deliveryForm, Model model) {

		String companyName = deliveryForm.getLogisticsCompanyName();
		if (StringUtils.isNotEmpty(deliveryForm.getLogisticsCompanyNameText())) {
			companyName = deliveryForm.getLogisticsCompanyNameText();
		}

		Customer customer = getCustomer();
		deliveryService.addDelibery(customer, companyName, deliveryForm);

		return "forward:createDelivery.htm";
	}

	@RequestMapping("/searchDelivery.htm")
	public String searchDelivery(Model model) {
		List<Category> categorys = categoryRepository.findAll();
		model.addAttribute("categorys", categorys);
		return "/context/delivery/deliveryList";
	}

	

}
