package com.mycloud.store.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired  
	protected  HttpServletRequest request;
	
	@Autowired  
	protected  HttpServletResponse response;
	
	protected void setPageheader(String sidebarName,String submenu,String title,String description,Model model){
		model.addAttribute("homepage", "/dashboard.htm");
		model.addAttribute("submenu", submenu);
		model.addAttribute("title", title);
		model.addAttribute("description", description);
	}

	
}
