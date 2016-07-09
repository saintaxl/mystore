/**
 * 
 */
package com.mycloud.store.controller.rest.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Shawn
 *
 */
public class JQueryDatatablesPage<T> implements Serializable{

	
    private static final long serialVersionUID = 4938715768462021618L;
    
    private Long iTotalRecords;
    
    private Long iTotalDisplayRecords;
    
    private Integer sEcho;
    
    private Map<String,Object> extension;
    
    private List<T> aaData;
    
	public Map<String, Object> getExtension() {
		return extension;
	}

	public void setExtension(Map<String, Object> extension) {
		this.extension = extension;
	}

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

}
