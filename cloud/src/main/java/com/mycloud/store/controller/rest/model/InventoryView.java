/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;

/**
 * @author Shawn
 *
 */
public class InventoryView implements Serializable{

    private static final long serialVersionUID = -312015269599764478L;

	private Integer id;
    
    private String barCode;
    
    private String productName;
    
    private String color;
    
    private Integer number;
    
    private QuantityView quantity;
    
    private CategoryView category;
    
    private Double volume;
    
    private Double weight;
    
    private String shelvesNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}


	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getShelvesNo() {
		return shelvesNo;
	}

	public void setShelvesNo(String shelvesNo) {
		this.shelvesNo = shelvesNo;
	}

	public QuantityView getQuantity() {
		return quantity;
	}

	public void setQuantity(QuantityView quantity) {
		this.quantity = quantity;
	}

	public CategoryView getCategory() {
		return category;
	}

	public void setCategory(CategoryView category) {
		this.category = category;
	}

}
