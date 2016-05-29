package com.mycloud.store.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.entity.Role;
import com.mycloud.entity.User;
import com.mycloud.enums.RoleEnum;
import com.mycloud.exception.BusinessException;
import com.mycloud.repository.RolesRepository;
import com.mycloud.repository.UserRepository;
import com.mycloud.store.exception.ErrorCode;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolesRepository rolesRepository;
	
	
	public void registerUser(User user){
		
		User existing = findUserByEmail(user.getEmail());
		if(existing!=null){
			throw new BusinessException(ErrorCode.ACCOUNT_EMAIL_EXISTING, user.getEmail());
		}
		
		Role role = rolesRepository.findByRole(RoleEnum.ROLE_USER.getValue());
		HashSet<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		
		userRepository.save(user);
	}
	
	

	public List<User> findAll() {
		// return userDao.findAll();
		return userRepository.findAll();
	}

	public User create(User user) {
		// return userDao.create(user);
		return userRepository.save(user);
	}

	public User findUserById(int id) {
		// return userDao.findUserById(id);
		return userRepository.findOne(id);
	}

	public User login(String email, String password) {
		// return userDao.login(email,password);
		// return userRepository.login(email,password);
		return userRepository.findByEmailAndPassword(email, password);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(int id) {
		userRepository.delete(id);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
