/**
 * 
 */
package com.mycloud.store.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shawn
 *
 */

@Controller
public class ErrorpageController {
	
	@RequestMapping("error_400.htm")
	public String showBadRequest(Model model){
		return "/common/error_400";
	}
	
	@RequestMapping("error_403.htm")
	public String showForbiddenError(Model model){
		return "/common/error_403";
	}
	
	@RequestMapping("error_404.htm")
	public String showBussinessError(Model model){
		return "/common/error_404";
	}
	
	@RequestMapping("error_405.htm")
	public String showMethodNotFound(Model model){
		return "/common/error_405";
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
