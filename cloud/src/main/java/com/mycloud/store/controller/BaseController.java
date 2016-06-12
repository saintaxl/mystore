package com.mycloud.store.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.mycloud.entity.Customer;
import com.mycloud.entity.Role;
import com.mycloud.store.service.CustomUserDetails;
import com.mycloud.store.sso.RoleInfo;
import com.mycloud.store.sso.UserInfo;

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
	
	protected UserInfo getUserInfo(){
		Object attribute = request.getSession().getAttribute("uservo");
		if(attribute ==null){
			return null;
		}
		return (UserInfo)attribute;
	}
	
	protected Customer getCustomer(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if(principal!=null){
			CustomUserDetails userDetails = (CustomUserDetails)principal;
			Customer customer = userDetails.getCustomer();
			return customer;
		}
		return null;
	}

	
}
