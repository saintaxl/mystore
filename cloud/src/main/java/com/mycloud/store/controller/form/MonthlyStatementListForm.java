/**
 * 
 */
package com.mycloud.store.controller.form;

import com.mycloud.constant.SettlementStatus;


/**
 * @author Shawn
 *
 */
public class MonthlyStatementListForm {
	
	
	private String settlementNo;
	
	private String month;
	
	private SettlementStatus status ;

	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public SettlementStatus getStatus() {
		return status;
	}

	public void setStatus(SettlementStatus status) {
		this.status = status;
	}
	
	
	
}
