/**
 * 
 */
package com.mycloud.store.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.entity.Role;
import com.mycloud.store.service.CustomUserDetails;
import com.mycloud.store.sso.RoleInfo;
import com.mycloud.store.sso.UserInfo;

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
		if(request.getSession().getAttribute("uservo") == null){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object principal = auth.getPrincipal();
			if(principal!=null){
				CustomUserDetails userDetails = (CustomUserDetails)principal;
				UserInfo userinfo = new UserInfo();
				userinfo.setCustomerName(userDetails.getCustomerName());
				userinfo.setEmail(userDetails.getEmail());
				userinfo.setMobile(userDetails.getMobile());
				Set<Role> roles = userDetails.getRoles();
				Set<RoleInfo> roleinfos = new HashSet<RoleInfo>();
				for (Role role : roles) {
					RoleInfo roleinfo = new RoleInfo();
					roleinfo.setRole(role.getRole());
					roleinfos.add(roleinfo);
                }
				userinfo.setRoleinfo(roleinfos);
				userinfo.setState(userDetails.getState());
				userinfo.setUsername(userDetails.getUsername());
				userinfo.setLogonTime(new Date());
				request.getSession().setAttribute("uservo", userinfo);
			}
		}
		
		return "default";
	}
	
	

}
