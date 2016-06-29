/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;

/**
 * @author Shawn
 *
 */
public class LogisticsView implements Serializable{

    private static final long serialVersionUID = -9101768617342129071L;
    
    private Integer id;
    
    private String companyName;
    
    private String logisticsNo;
    
    private String note;
    
    private String arrivalDate;
    
    private String name;
    
    private String mobile;
    
    private String province;
    
    private String city;
    
    private String district;
    
    private String address;
    
    private Double price;
    
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
    

}
