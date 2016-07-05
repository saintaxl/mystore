/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mycloud.constant.SettlementStatus;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "DAILY_SETTLEMENT")
public class DailySettlement extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 4337942924527302745L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
    
    @Column(name="TOTAL_WEIGHT", nullable=false)
    private Double totalWeight;
    
    @Column(name="TOTAL_VOLUME", nullable=false)
    private Double totalVolume;
    
    @Column(name="TOTAL_NUMBER", nullable=false)
    private Integer totalNumber;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    
    @Column(name="PRICE")
	private BigDecimal price;
    
    @Temporal(TemporalType.DATE)
	@Column(name="SETTLEMENT_DATE",insertable=true, updatable = false)
	private Date settlementDate;
    
    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private SettlementStatus status = SettlementStatus.PENDING;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MONTHLY_STATEMENT_ID", nullable = true)
    private MonthlyStatement monthlyStatement;
    
	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SettlementStatus getStatus() {
		return status;
	}

	public void setStatus(SettlementStatus status) {
		this.status = status;
	}

	public MonthlyStatement getMonthlyStatement() {
		return monthlyStatement;
	}

	public void setMonthlyStatement(MonthlyStatement monthlyStatement) {
		this.monthlyStatement = monthlyStatement;
	}
   

    

}
