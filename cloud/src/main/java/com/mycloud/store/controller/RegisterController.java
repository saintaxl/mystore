/**
 * 
 */
package com.mycloud.store.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycloud.entity.Role;
import com.mycloud.entity.User;
import com.mycloud.enums.State;
import com.mycloud.store.service.CustomUserDetails;
import com.mycloud.store.service.CustomUserDetailsService;
import com.mycloud.store.service.UserService;

/**
 * @author Shawn
 *
 */

@Controller
public class RegisterController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerUser(@RequestParam(value = "fullname", required = true) String name,
	        @RequestParam(value = "logonname", required = true) String username, @RequestParam(value = "email", required = true) String email,
	        @RequestParam(value = "mobile", required = true) String mobile, @RequestParam(value = "newpassword", required = true) String password) {

		User user = new User();
		user.setEmail(email);
		user.setMobile(mobile);
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		user.setName(name);
		user.setUsername(username);
		user.setPassword(hashedPassword);
		user.setState(State.ACTIVE.getState());
		try {
			userService.registerUser(user);
		} catch (Exception e) {
			response.setHeader("errorPage", "logon");
			response.setHeader("fromPage", "register");
			throw e;
		}

		UserDetails customUserDetails = new CustomUserDetails(user);

		// UsernamePasswordAuthenticationToken authentication = new
		// UsernamePasswordAuthenticationToken(email, hashedPassword);
		// authentication.setAuthenticated(true);

		Set<SimpleGrantedAuthority> authoritys = new HashSet<SimpleGrantedAuthority>();
		for (Role role : user.getRoles()) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
			authoritys.add(authority);
		}

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails, password, authoritys);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:homepage.htm";
	}

}
