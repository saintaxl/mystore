/**
 * 
 */
package com.mycloud.store.controller.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Shawn
 *
 */
public class DeliveryListForm {
	
	
	private String deliveryNo;
	
	private String productName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date from;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date to;
	
	private String logisticsCompanyName;
	
	private String logisticsNo;
	
	private Integer category;
	
	private String barCode;

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
}
