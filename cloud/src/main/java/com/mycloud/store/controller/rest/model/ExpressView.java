/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;
import java.util.List;

import com.mycloud.constant.ExpressStatus;

/**
 * @author Shawn
 *
 */
public class ExpressView implements Serializable{


    private static final long serialVersionUID = 5275033183754072198L;

	private Integer id;
    
    private String expressNo;
    
    private List<ExpressDetailView> details;
    
    private ExpressStatus status ;    
    
    private CustomerView customer;
    
    private LogisticsView logistics;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public List<ExpressDetailView> getDetails() {
		return details;
	}

	public void setDetails(List<ExpressDetailView> details) {
		this.details = details;
	}

	public ExpressStatus getStatus() {
		return status;
	}

	public void setStatus(ExpressStatus status) {
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
