/**
 * 
 */
package com.mycloud.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shawn
 *
 */
@Controller
public class HomeController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/index.htm")
	public String index(Model model) {
		return "index";
		//return baseController.showSystemsError(model);
	}
	
	@RequestMapping("/homepage.htm")
	public String homepage(Model model){
		return "default";
	}
	
	

}
