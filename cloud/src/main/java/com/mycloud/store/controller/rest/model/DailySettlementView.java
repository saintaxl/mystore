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
public class DailySettlementView implements Serializable{

    private static final long serialVersionUID = 5585125140311263285L;

	private Integer id;
    
    private Double totalWeight;
    
    private Double totalVolume;
    
    private Integer totalNumber;
    
	private BigDecimal price;
    
	private String settlementDate;
	
	private MonthlyStatementView monthlyStatement;
	
	private CustomerView customer;
    
    private SettlementStatus status ;
    
	public MonthlyStatementView getMonthlyStatement() {
		return monthlyStatement;
	}

	public void setMonthlyStatement(MonthlyStatementView monthlyStatement) {
		this.monthlyStatement = monthlyStatement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(Double totalVolume) {
		this.totalVolume = totalVolume;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public CustomerView getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public SettlementStatus getStatus() {
		return status;
	}

	public void setStatus(SettlementStatus status) {
		this.status = status;
	}
    
}
