/**
 * 
 */
package com.mycloud.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycloud.exception.BusinessException;
import com.mycloud.store.exception.ErrorCode;

/**
 * @author Shawn
 *
 */
@Controller
public class LogonController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(LogonController.class);
	
	@RequestMapping(value = "/logon.htm", method = {RequestMethod.GET, RequestMethod.POST})
	//@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout,
	public ModelAndView login(@RequestParam(value = "email", required = false) String email,HttpServletRequest request) {
		
		//SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && !authentication.getPrincipal().equals("anonymousUser")){
			return new ModelAndView("redirect:/homepage.htm");
		}
		
		ModelAndView model = new ModelAndView();
		Exception exception = (Exception)request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION"); 
		if(exception!=null){
			response.setHeader("errorPage", "logon");
			response.setHeader("fromPage", "login");
			throw new BusinessException(ErrorCode.ACCOUNT_EMAIL_EXISTING, email);
		}
		model.setViewName("logon");
		return model;
	}
	
	

}
