/**
 * 
 */
package com.mycloud.store.controller.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.store.controller.BaseController;

/**
 * @author Shawn
 *
 */
@Controller
public class DashboardController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@RequestMapping("/dashboard.htm")
	public String dashboard(Model model) {
		model.addAttribute("hello", "hello,world");
		return "/context/dashboard";
	}

}
