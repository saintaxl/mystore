/**
 * 
 */
package com.mycloud.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shawn
 *
 */

@Controller
public class ErrorpageController {
	
	@RequestMapping("error_403.htm")
	//@ExceptionHandler(PiranhaException.class)
	public String showForbiddenError(Model model){
		return "/common/error_403";
	}
	
	@RequestMapping("error_404.htm")
	//@ExceptionHandler(PiranhaException.class)
	public String showBussinessError(Model model){
		return "/common/error_404";
	}
	
	@RequestMapping("error_500.htm")
	public String showSystemsError(Model model){
		return "/common/error_500";
	}
	
	@RequestMapping("internal_404.htm")
	public String showInternalBussinessError(Model model){
		return "/common/internal_404";
	}
	
	@RequestMapping("internal_500.htm")
	public String showInternalSystemsError(Model model){
		return "/common/internal_500";
	}

}
