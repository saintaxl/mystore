/**
 * 
 */
package com.mycloud.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.entity.User;
import com.mycloud.enums.State;
import com.mycloud.exception.BusinessException;
import com.mycloud.store.exception.ErrorCode;

/**
 * @author Shawn
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.findUserByEmail(username);
		if(user == null){
			throw new UsernameNotFoundException("UserName "+username+" not found");
		}
		if(!user.getState().equals(State.ACTIVE.getState())){
			throw new BusinessException(ErrorCode.ACCOUNT_STATUS_NOT_NORMAL);
		}
		
		return new CustomUserDetails(user);
	}

}
