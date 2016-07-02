/**
 * 
 */
package com.mycloud.store.controller.form;

import com.mycloud.constant.ExpressStatus;


/**
 * @author Shawn
 *
 */
public class ExpressListForm {
	
	
	private String expressNo;
	
	private String productName;
	
	private Integer category;
	
	private String barCode;
	
	private String logisticsCompanyName;
	
	private String logisticsNo;
	
	private String name;
	
	private String mobile;
	
	private ExpressStatus status ;
	
	public ExpressStatus getStatus() {
		return status;
	}

	public void setStatus(ExpressStatus status) {
		this.status = status;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
	
}
