package com.mycloud.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycloud.entity.Customer;
import com.mycloud.entity.Inventory;
import com.mycloud.repository.InventoryRepository;
import com.mycloud.store.controller.form.InventoryListForm;

@Service
@Transactional
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	public Page<Inventory> searchInventory(final InventoryListForm inventoryListForm,final Customer customer, Pageable pageable) {
		
		Specification<Inventory> spec = new Specification<Inventory>() {
			public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(cb.equal(root.get("customer").get("id").as(Integer.class), customer.getId() ));
				
				if (StringUtils.isNotEmpty(inventoryListForm.getBarCode())) {
					list.add(cb.like(root.get("barCode").as(String.class), "%" + inventoryListForm.getBarCode() + "%"));
				}
				if (StringUtils.isNotEmpty(inventoryListForm.getShelvesNo())) {
					list.add(cb.equal(root.get("shelvesNo").as(String.class),  inventoryListForm.getShelvesNo() ));
				}
				if (StringUtils.isNotEmpty(inventoryListForm.getProductName())) {
					list.add(cb.like(root.get("productName").as(String.class), "%" + inventoryListForm.getProductName() + "%"));
				}
				if (inventoryListForm.getCategory()!=null) {
					list.add(cb.equal(root.get("category").get("id").as(Integer.class), inventoryListForm.getCategory() ));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		
		Page<Inventory> findAll = inventoryRepository.findAll(spec, pageable);
		return findAll;
    }
	
	
	public void dailySettlement(){
		/*
		Specification<Inventory> spec = new Specification<Inventory>() {
			public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Expression<Double> sumWeight = cb.sum(root.<Double>get("weight"));
				Expression<Double> sumVolume = cb.sum(root.<Double>get("weight"));
                query.multiselect(
                		          sumWeight, 
                		          sumVolume,
                		          root.get("customer").get("id")
                		         );
                query.groupBy(root.get("customer").get("id"));
                
                
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.greaterThan(root.<Integer>get("number"), 0));
                
                Predicate[] p = new Predicate[list.size()];
                query.where(list.toArray(p));
                
				return query.getRestriction();  
			}
		};*/
		
		//List<Inventory> inventoryList = inventoryRepository..findAll(spec);
	}

}
