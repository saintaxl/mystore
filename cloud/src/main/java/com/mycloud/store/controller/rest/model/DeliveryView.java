/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;
import java.util.List;

import com.mycloud.constant.DeliveryStatus;

/**
 * @author Shawn
 *
 */
public class DeliveryView implements Serializable{

    private static final long serialVersionUID = -7326945628610214041L;

	private Integer id;
    
    private String deliveryNo;
    
    private List<DeliveryDetailView> details;
    
    private DeliveryStatus status ;    
    
    private CustomerView customer;
    
    private LogisticsView logistics;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public List<DeliveryDetailView> getDetails() {
		return details;
	}

	public void setDetails(List<DeliveryDetailView> details) {
		this.details = details;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}

	public CustomerView getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}

	public LogisticsView getLogistics() {
		return logistics;
	}

	public void setLogistics(LogisticsView logistics) {
		this.logistics = logistics;
	}
    
	
}
