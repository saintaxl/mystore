package com.mycloud.store.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Delivery;
import com.mycloud.entity.Logistics;
import com.mycloud.entity.Quantity;
import com.mycloud.repository.CategoryRepository;
import com.mycloud.repository.CustomerRepository;
import com.mycloud.repository.DeliveryRepository;
import com.mycloud.repository.LogisticsRepository;
import com.mycloud.repository.QuantityRepository;

@Service
@Transactional
public class DeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private QuantityRepository quantityRepository;
	
	
	
	public Delivery addDelivery(String barCode,Integer categoryId,Integer quantityId, String color,Customer customer,String deliveryNo,
			String note,Integer number,String productName,Double volume,Double weight, Logistics logistics){
		Delivery delivery = new Delivery();
		delivery.setBarCode(barCode);
		
		Category category = categoryRepository.findOne(categoryId);
		delivery.setCategory(category);

		
		delivery.setColor(color);
		delivery.setCustomer(customer);
		delivery.setDeliveryNo(deliveryNo);
		

		delivery.setLogistics(logistics);
		delivery.setNote(note);
		delivery.setNumber(number);
		delivery.setProductName(productName);
		
		Quantity quantity = quantityRepository.findOne(quantityId);
		delivery.setQuantity(quantity);
		delivery.setVolume(volume);
		delivery.setWeight(weight);
		
		deliveryRepository.save(delivery);
		return delivery;
	}

}
