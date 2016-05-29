package com.mycloud.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/test.htm")
	public String test(Model model) {
		model.addAttribute("rants", "hello,world");
		return "/context/inbox";
	}
	
	@RequestMapping("/try.htm")
	public String tryl(Model model) {
		model.addAttribute("rants", "hello,world");
		return "/context/mail";
	}

}
