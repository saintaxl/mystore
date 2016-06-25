/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;
import java.util.Date;

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
