/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mycloud.constant.SettlementStatus;

/**
 * @author Shawn
 *
 */
public class MonthlyStatementView implements Serializable{
	
    private static final long serialVersionUID = -3810555447803368094L;

	private Integer id;

	private SettlementStatus status;
	
	private String month;
	
	private String settlementNo;
	
	private BigDecimal amount;
	
    private CustomerView customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SettlementStatus getStatus() {
		return status;
	}

	public void setStatus(SettlementStatus status) {
		this.status = status;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CustomerView getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}
    
}
