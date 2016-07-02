/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mycloud.constant.LogisticsCompanyType;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "LOGISTICS_COMPANY")
public class LogisticsCompany extends AbstractEntity implements Serializable {


    private static final long serialVersionUID = 6769530548031052160L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;
	
	@Column(name="COMPANY_TYPE")
    @Enumerated(EnumType.STRING)
	private LogisticsCompanyType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public LogisticsCompanyType getType() {
		return type;
	}

	public void setType(LogisticsCompanyType type) {
		this.type = type;
	}
	
	
}
