/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Shawn
 *
 */
public class JQueryDatatablesPage<T> implements Serializable{

	
    private static final long serialVersionUID = 4938715768462021618L;
    
    private Long iTotalRecords;
    
    private int iTotalDisplayRecords;
    
    private String sEcho;
    
    private List<T> aaData;

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

}
