/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mycloud.constant.LogisticsType;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "LOGISTICS")
public class Logistics extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 7671604634523753831L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name = "LOGISTICS_NO")
	private String logisticsNo;
	
	@Column(name="NOTE")
    private String note;
	
	@Column(name="ADDRESS")
    private String address;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="PROVINCE")
	private String province;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="DISTRICT")
	private String district;
	
	@Column(name="PACKAGE")
	private boolean needPackage = false;
	
	@Column(name="INSURANCE")
	private boolean insurance = false;
	
	@Column(name="LOGISTICS_TYPE")
    @Enumerated(EnumType.STRING)
	private LogisticsType logisticsType;
	
	@Column(name="PRICE")
	private Double price;
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="ARRIVAL_DATE",insertable=true, updatable = false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ")
	private Date arrivalDate;
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public boolean isNeedPackage() {
		return needPackage;
	}

	public void setNeedPackage(boolean needPackage) {
		this.needPackage = needPackage;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public LogisticsType getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(LogisticsType logisticsType) {
		this.logisticsType = logisticsType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
