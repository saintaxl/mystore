/**
 * 
 */
package com.mycloud.store.controller.form;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Shawn
 *
 */
public class ExpressForm {
	
	private List<Integer> inventoryId;
	
	private List<Integer> number;
	
	private Integer logisticsCompany;
	
	private String logisticsNo;
	
	private String address;
	
	private String name;
	
	private String mobile;
	
	private String province;
	
	private String expressNo;
	
	private String city;
	
	private String district;
	
	private Boolean needPackage;
	
	private Boolean insurance;
	
	private BigDecimal price;

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public List<Integer> getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(List<Integer> inventoryId) {
		this.inventoryId = inventoryId;
	}

	public List<Integer> getNumber() {
		return number;
	}

	public void setNumber(List<Integer> number) {
		this.number = number;
	}

	public Integer getLogisticsCompany() {
		return logisticsCompany;
	}

	public void setLogisticsCompany(Integer logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

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

	public Boolean getNeedPackage() {
		return needPackage;
	}

	public void setNeedPackage(Boolean needPackage) {
		this.needPackage = needPackage;
	}

	public Boolean getInsurance() {
		return insurance;
	}

	public void setInsurance(Boolean insurance) {
		this.insurance = insurance;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

}
