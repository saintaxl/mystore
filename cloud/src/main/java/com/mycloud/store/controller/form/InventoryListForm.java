/**
 * 
 */
package com.mycloud.store.controller.form;


/**
 * @author Shawn
 *
 */
public class InventoryListForm {
	
	private String barCode;
	
	private String productName;
	
	private String shelvesNo;
	
	private Integer category;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShelvesNo() {
		return shelvesNo;
	}

	public void setShelvesNo(String shelvesNo) {
		this.shelvesNo = shelvesNo;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	
	
	
	
}
