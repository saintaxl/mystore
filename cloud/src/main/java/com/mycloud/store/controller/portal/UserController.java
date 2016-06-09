package com.mycloud.store.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.mycloud.entity.User;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.service.CustomUserDetails;
import com.mycloud.store.service.UserService;

@Controller
public class UserController extends BaseController{

	@Autowired
	private  UserService userService;
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			User loginUser = userService.findUserByEmail(email);
			return new CustomUserDetails(loginUser);
		}

		return null;
	}

}
