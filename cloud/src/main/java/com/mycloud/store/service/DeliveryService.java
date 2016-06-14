package com.mycloud.store.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
	
	@Autowired
	private LogisticsService logisticsService;
	
	
	public Logistics addsingleDelibery(Customer customer ,Date arrivalDate, String companyName,String logisticsNo, List<String> productName,List<String> barCode,
			List<Integer> category,List<Integer> quantity,List<String> color,List<Integer> number,List<String> note,List<Double> volume,List<Double> weight,String deliveryNo){
		Logistics logistics = logisticsService.addLogistics(arrivalDate, companyName, logisticsNo);
		
		for (int i = 0; i < productName.size(); i++) {
			String barCodeStr = CollectionUtils.isNotEmpty(barCode) && barCode.get(i)!=null ? barCode.get(i) : "" ;
			Integer categoryId = category.get(i);
			Integer quantityId = quantity.get(i);
			String colorStr = CollectionUtils.isNotEmpty(color) && color.get(i)!=null ? color.get(i) : "" ;
			String noteStr = CollectionUtils.isNotEmpty(note) && note.get(i)!=null ? note.get(i) : "" ;
			Integer numberParm = CollectionUtils.isNotEmpty(number) && number.get(i)!=null ? number.get(i) : 0;
			String productNameStr = CollectionUtils.isNotEmpty(productName) && productName.get(i)!=null ? productName.get(i) : "" ;
			Double volumeParm = CollectionUtils.isNotEmpty(volume) && volume.get(i)!=null ? volume.get(i) : 0;
			Double weightParm = CollectionUtils.isNotEmpty(weight) && weight.get(i)!=null ? weight.get(i) : 0;
			addDelivery(barCodeStr, categoryId, quantityId, colorStr, customer, deliveryNo, noteStr, numberParm, productNameStr, volumeParm, weightParm, logistics);
        }
		
		return logistics;
	}
	
	
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
