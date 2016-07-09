/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mycloud.constant.SettlementStatus;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "MONTHLY_STATEMENT")
public class MonthlyStatement extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 4337942924527302745L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private SettlementStatus status = SettlementStatus.PENDING;
	
	@OneToMany(mappedBy = "monthlyStatement", fetch = FetchType.LAZY)
    private List<DailySettlement> dailySettlement;
	
	@Column(name="MONTH")
	private String month;
	
	@Column(name="SETTLEMENT_NO")
	private String settlementNo;
	
	@Column(name="AMOUNT")
	private BigDecimal amount;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
	
	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

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

	public List<DailySettlement> getDailySettlement() {
		return dailySettlement;
	}

	public void setDailySettlement(List<DailySettlement> dailySettlement) {
		this.dailySettlement = dailySettlement;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
