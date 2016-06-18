package com.mycloud.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
import com.mycloud.store.controller.form.DeliveryForm;
import com.mycloud.store.controller.form.DeliveryListForm;

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

	public Logistics addsingleDelibery(Customer customer, String companyName, DeliveryForm deliveryForm){
		
		Logistics logistics = logisticsService.addLogistics(deliveryForm.getLogisticsDate(), companyName, deliveryForm.getLogisticsNo());

		for (int i = 0; i < deliveryForm.getProductName().size(); i++) {
			String barCodeStr = CollectionUtils.isNotEmpty(deliveryForm.getBarCode()) && deliveryForm.getBarCode().get(i) != null ? deliveryForm.getBarCode().get(i) : "";
			Integer categoryId = deliveryForm.getCategory().get(i);
			Integer quantityId = deliveryForm.getQuantity().get(i);
			String colorStr = CollectionUtils.isNotEmpty(deliveryForm.getColor()) && deliveryForm.getColor().get(i) != null ? deliveryForm.getColor().get(i) : "";
			String noteStr = CollectionUtils.isNotEmpty(deliveryForm.getNote()) && deliveryForm.getNote().get(i) != null ? deliveryForm.getNote().get(i) : "";
			Integer numberParm = CollectionUtils.isNotEmpty(deliveryForm.getNumber()) && deliveryForm.getNumber().get(i) != null ? deliveryForm.getNumber().get(i) : 0;
			String productNameStr = CollectionUtils.isNotEmpty(deliveryForm.getProductName()) && deliveryForm.getProductName().get(i) != null ? deliveryForm.getProductName().get(i) : "";
			Double volumeParm = CollectionUtils.isNotEmpty(deliveryForm.getVolume()) && deliveryForm.getVolume().get(i) != null ? deliveryForm.getVolume().get(i) : 0;
			Double weightParm = CollectionUtils.isNotEmpty(deliveryForm.getWeight()) && deliveryForm.getWeight().get(i) != null ? deliveryForm.getWeight().get(i) : 0;
			addDelivery(barCodeStr, categoryId, quantityId, colorStr, customer, deliveryForm.getDeliveryNo(), noteStr, numberParm, productNameStr, volumeParm, weightParm,logistics);
		}

		return logistics;
	}

	public Delivery addDelivery(String barCode, Integer categoryId, Integer quantityId, String color, Customer customer, String deliveryNo, String note,
	        Integer number, String productName, Double volume, Double weight, Logistics logistics) {
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

	public Page<Delivery> searchDelivery(final DeliveryListForm deliveryListForm, Pageable pageable) {
		
		Specification<Delivery> spec = new Specification<Delivery>() {
			public Predicate toPredicate(Root<Delivery> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringUtils.isNotEmpty(deliveryListForm.getBarCode())) {
					list.add(cb.like(root.get("barCode").as(String.class), "%" + deliveryListForm.getBarCode() + "%"));
				}
				/*if (name.length() > 0) {
					list.add(cb.equal(root.get("uuid").as(Integer.class), name));
				}*/
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		
		Page<Delivery> findAll = deliveryRepository.findAll(spec, pageable);
		return findAll;
    }



}
