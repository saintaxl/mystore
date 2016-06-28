/**
 * 
 */
package com.mycloud.store.controller.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.ExpressForm;
import com.mycloud.util.PrimaryGenerater;

/**
 * @author Shawn
 *
 */
@Controller
public class ExpressController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(ExpressController.class);

	@RequestMapping("/createExpress.htm")
	public String createDelivery(Model model) {
		String expressNo = PrimaryGenerater.getInstance().generaterNextNumber();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(date);

		model.addAttribute("expressNo", expressNo);
		model.addAttribute("today", today);
		return "/context/express/expressForm";
	}
	
	
	@RequestMapping("/submitExpress.htm")
	public String submitDelivery(@ModelAttribute ExpressForm deliveryForm, Model model) {

		System.out.println("adsd");

		return "forward:createExpress.htm";
	}

	
	

}
