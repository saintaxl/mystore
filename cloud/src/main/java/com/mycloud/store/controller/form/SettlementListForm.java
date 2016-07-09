/**
 * 
 */
package com.mycloud.store.controller.form;

import com.mycloud.constant.SettlementStatus;


/**
 * @author Shawn
 *
 */
public class SettlementListForm {
	
	
	private String month;
	
	private SettlementStatus status;
	
	public SettlementStatus getStatus() {
		return status;
	}

	public void setStatus(SettlementStatus status) {
		this.status = status;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	
}
