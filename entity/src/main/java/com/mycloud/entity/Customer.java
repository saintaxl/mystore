/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Shawn
 *
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractEntity implements Serializable{
	
    private static final long serialVersionUID = -6410239285964791201L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CUSTOMER_NO",nullable=false , length=80)
	private String customerNo; 
	
	@Column(name="CUSTOMER_NAME",nullable=false , length=80)
	private String customerName;
	
	@Column(name="ACRONYM",nullable=false , length=255)
	private String acronym;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Delivery> deliverys;
	
	public List<Delivery> getDeliverys() {
		return deliverys;
	}

	public void setDeliverys(List<Delivery> deliverys) {
		this.deliverys = deliverys;
	}

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
