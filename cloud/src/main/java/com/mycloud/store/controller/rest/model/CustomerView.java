/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;

/**
 * @author Shawn
 *
 */
public class CustomerView implements Serializable{

    private static final long serialVersionUID = -270604417696373926L;

	private Integer id;
	
	private String customerNo; 
	
	private String customerName;
	
	private String acronym;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	

}
