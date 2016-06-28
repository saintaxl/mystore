package com.mycloud.store.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.util.DigestUtils;

import com.mycloud.constant.DeliveryStatus;
import com.mycloud.constant.LogisticsType;
import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Delivery;
import com.mycloud.entity.DeliveryDetails;
import com.mycloud.entity.Logistics;
import com.mycloud.entity.Quantity;
import com.mycloud.exception.BusinessException;
import com.mycloud.repository.CategoryRepository;
import com.mycloud.repository.CustomerRepository;
import com.mycloud.repository.DeliveryDetailsRepository;
import com.mycloud.repository.DeliveryRepository;
import com.mycloud.repository.QuantityRepository;
import com.mycloud.store.controller.form.DeliveryForm;
import com.mycloud.store.controller.form.DeliveryListForm;
import com.mycloud.store.exception.ErrorCode;

@Service
@Transactional
public class DeliveryService {
	
	@Autowired
	private DeliveryDetailsRepository deliveryDetailsRepository;

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

	public void addDelibery(Customer customer, String companyName, DeliveryForm deliveryForm){
		
		Logistics logistics = new Logistics();
		logistics.setArrivalDate(deliveryForm.getLogisticsDate());
		logistics.setCompanyName(companyName);
		logistics.setLogisticsNo(deliveryForm.getLogisticsNo());
		logistics.setLogisticsType(LogisticsType.DELIVERY);
		
		logisticsService.saveLogistics(logistics);
		
		Delivery delivery = new Delivery();
		delivery.setCustomer(customer);
		delivery.setLogistics(logistics);
		delivery.setDeliveryNo(deliveryForm.getDeliveryNo());
		delivery.setStatus(DeliveryStatus.SHIPPED);
		deliveryRepository.save(delivery);
		
		for (int i = 0; i < deliveryForm.getProductName().size(); i++) {
			String barCodeStr = CollectionUtils.isNotEmpty(deliveryForm.getBarCode()) && deliveryForm.getBarCode().get(i) != null ? deliveryForm.getBarCode().get(i).trim() : "";
			Integer categoryId = deliveryForm.getCategory().get(i);
			Integer quantityId = deliveryForm.getQuantity().get(i);
			String colorStr = CollectionUtils.isNotEmpty(deliveryForm.getColor()) && deliveryForm.getColor().get(i) != null ? deliveryForm.getColor().get(i).trim() : "";
			String noteStr = CollectionUtils.isNotEmpty(deliveryForm.getNote()) && deliveryForm.getNote().get(i) != null ? deliveryForm.getNote().get(i).trim() : "";
			Integer numberParm = CollectionUtils.isNotEmpty(deliveryForm.getNumber()) && deliveryForm.getNumber().get(i) != null ? deliveryForm.getNumber().get(i) : 0;
			String productNameStr = CollectionUtils.isNotEmpty(deliveryForm.getProductName()) && deliveryForm.getProductName().get(i) != null ? deliveryForm.getProductName().get(i).trim() : "";
			Double volumeParm = CollectionUtils.isNotEmpty(deliveryForm.getVolume()) && deliveryForm.getVolume().get(i) != null ? deliveryForm.getVolume().get(i) : 0;
			Double weightParm = CollectionUtils.isNotEmpty(deliveryForm.getWeight()) && deliveryForm.getWeight().get(i) != null ? deliveryForm.getWeight().get(i) : 0;
			addDeliveryDetails(barCodeStr, categoryId, quantityId, colorStr, customer, delivery, noteStr, numberParm, productNameStr, volumeParm, weightParm);
		}
	}

	private void addDeliveryDetails(String barCode, Integer categoryId, Integer quantityId, String color, Customer customer, Delivery delivery, String note,
	        Integer number, String productName, Double volume, Double weight) {
		
		DeliveryDetails details = new DeliveryDetails();
		details.setBarCode(barCode);
		Category category = categoryRepository.findOne(categoryId);
		details.setCategory(category);
		details.setColor(color);
		details.setNote(note);
		details.setNumber(number);
		details.setProductName(productName);
		Quantity quantity = quantityRepository.findOne(quantityId);
		details.setQuantity(quantity);
		details.setVolume(volume);
		details.setWeight(weight);
		details.setDelivery(delivery);
		details.setCustomer(customer);
		String md5 = customer.getCustomerNo() + productName + category.getId();
		try {
	        String md5DigestAsHex = DigestUtils.md5DigestAsHex(md5.getBytes("UTF-8"));
	        details.setMd5(md5DigestAsHex);
        } catch (UnsupportedEncodingException e) {
	        throw new BusinessException(ErrorCode.MD5_DIGEST_ASHEX_ERROR);
        }
		
		deliveryDetailsRepository.save(details);
	}

	public Page<DeliveryDetails> searchDetails(final DeliveryListForm deliveryListForm,final Customer customer, Pageable pageable) {
		
		Specification<DeliveryDetails> spec = new Specification<DeliveryDetails>() {
			public Predicate toPredicate(Root<DeliveryDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("customer").get("id").as(Integer.class), customer.getId() ));
				list.add(cb.equal(root.get("delivery").get("logistics").get("logisticsType").as(LogisticsType.class), LogisticsType.DELIVERY ));
				
				if (StringUtils.isNotEmpty(deliveryListForm.getBarCode())) {
					list.add(cb.like(root.get("barCode").as(String.class), "%" + deliveryListForm.getBarCode() + "%"));
				}
				if (StringUtils.isNotEmpty(deliveryListForm.getDeliveryNo())) {
					list.add(cb.equal(root.get("delivery").get("deliveryNo").as(String.class),  deliveryListForm.getDeliveryNo() ));
				}
				if (StringUtils.isNotEmpty(deliveryListForm.getProductName())) {
					list.add(cb.like(root.get("productName").as(String.class), "%" + deliveryListForm.getProductName() + "%"));
				}
				if (StringUtils.isNotEmpty(deliveryListForm.getLogisticsCompanyName())) {
					list.add(cb.like(root.get("delivery").get("logistics").get("companyName").as(String.class), "%" + deliveryListForm.getLogisticsCompanyName() + "%"));
				}
				if (StringUtils.isNotEmpty(deliveryListForm.getLogisticsNo())) {
					list.add(cb.equal(root.get("delivery").get("logistics").get("logisticsNo").as(String.class),  deliveryListForm.getLogisticsNo() ));
				}
				if (deliveryListForm.getFrom()!=null) {
					list.add(cb.greaterThanOrEqualTo(root.get("delivery").get("logistics").get("arrivalDate").as(Date.class),  deliveryListForm.getFrom() ));
				}
				if (deliveryListForm.getTo()!=null) {
					list.add(cb.lessThanOrEqualTo(root.get("delivery").get("logistics").get("arrivalDate").as(Date.class),  deliveryListForm.getTo() ));
				}
				if (deliveryListForm.getCategory()!=null) {
					list.add(cb.equal(root.get("category").get("id").as(Integer.class), deliveryListForm.getCategory() ));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		
		Page<DeliveryDetails> findAll = deliveryDetailsRepository.findAll(spec, pageable);
		return findAll;
    }



}
