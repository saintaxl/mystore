/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;

/**
 * @author Shawn
 *
 */
public class DeliveryDetailView implements Serializable{

	
    private static final long serialVersionUID = 4302281344363763855L;
    
    private Integer id;
    
    private QuantityView quantity;
    
    private String barCode;
    
    private String productName;
    
    private String color;
    
    private Integer number;
    
    private Double volume;
    
    private Double weight;
    
    private String note;
    
    private CategoryView category;
    
    private CustomerView customer;
    
    private DeliveryView delivery;
    
	public DeliveryView getDelivery() {
		return delivery;
	}

	public void setDelivery(DeliveryView delivery) {
		this.delivery = delivery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuantityView getQuantity() {
		return quantity;
	}

	public void setQuantity(QuantityView quantity) {
		this.quantity = quantity;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public CategoryView getCategory() {
		return category;
	}

	public void setCategory(CategoryView category) {
		this.category = category;
	}

	public CustomerView getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}
	

}
