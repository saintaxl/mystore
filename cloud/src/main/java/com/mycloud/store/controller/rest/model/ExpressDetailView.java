/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;

/**
 * @author Shawn
 *
 */
public class ExpressDetailView implements Serializable{

	
    private static final long serialVersionUID = 4302281344363763855L;
    
    private Integer id;
    
    private Integer number;
    
    private CustomerView customer;
    
    private ExpressView express;
    
    private InventoryView inventory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public CustomerView getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}

	public ExpressView getExpress() {
		return express;
	}

	public void setExpress(ExpressView express) {
		this.express = express;
	}

	public InventoryView getInventory() {
		return inventory;
	}

	public void setInventory(InventoryView inventory) {
		this.inventory = inventory;
	}
    

}
