/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mycloud.constant.ExpressStatus;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "EXPRESS")
public class Express extends AbstractEntity implements Serializable {
	
 
    private static final long serialVersionUID = -2729741442931186437L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGISTICS_ID", nullable = false)
    private Logistics logistics;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
	
	@Column(name="EXPRESS_NO")
	private String expressNo;
	
	@Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private ExpressStatus status = ExpressStatus.SHIPPED;
	
	@OneToMany(mappedBy = "express", fetch = FetchType.LAZY)
    private List<ExpressDetails> details;
	
	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public ExpressStatus getStatus() {
		return status;
	}

	public void setStatus(ExpressStatus status) {
		this.status = status;
	}

	public List<ExpressDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ExpressDetails> details) {
		this.details = details;
	}
	
}
