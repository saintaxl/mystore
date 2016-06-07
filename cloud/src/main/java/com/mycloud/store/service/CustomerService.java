package com.mycloud.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.entity.Customer;
import com.mycloud.repository.CustomerRepository;
import com.mycloud.util.PrimaryGenerater;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer addCustomer(String customerName){
		Customer customer = new Customer();
		customer.setCustomerName(customerName);
		customer.setCustomerNo(PrimaryGenerater.getInstance().generaterNextNumber());
		customerRepository.save(customer);
		return customer;
	}
	
	
}
