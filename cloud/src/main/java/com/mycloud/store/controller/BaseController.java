package com.mycloud.store.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;

import com.mycloud.entity.Customer;
import com.mycloud.entity.Role;
import com.mycloud.exception.BusinessException;
import com.mycloud.store.exception.ErrorCode;
import com.mycloud.store.service.CustomUserDetails;
import com.mycloud.store.sso.RoleInfo;
import com.mycloud.store.sso.UserInfo;
import com.mycloud.store.utils.Pinyin;

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
	
	protected String getBarCode(String customerNo,String customerAcronym,String category) {
		String categoryAcronym = Pinyin.String2Alpha(category);
		String md5 = customerNo + customerAcronym + categoryAcronym;
		String md5value = null;
		try {
			String md5DigestAsHex = DigestUtils.md5DigestAsHex(md5.getBytes("UTF-8"));
			md5value = StringUtils.substring(md5DigestAsHex, 0, 6).toUpperCase();
        } catch (UnsupportedEncodingException e) {
	        throw new BusinessException(ErrorCode.MD5_DIGEST_ASHEX_ERROR);
        }
		String returnvalue = customerAcronym + "-" + categoryAcronym + "-" + md5value;
		return returnvalue;
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
