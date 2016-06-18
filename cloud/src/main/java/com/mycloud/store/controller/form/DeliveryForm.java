/**
 * 
 */
package com.mycloud.store.controller.form;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Shawn
 *
 */
public class DeliveryForm {
	
	private String deliveryNo;
	
	private List<String> productName;
	
	private List<String> barCode;
	
	private List<Integer> category;
	
	private List<String> color;
	
	private List<Integer> number;
	
	private List<Integer> quantity;
	
	private List<Double> volume;
	
	private List<Double> weight;
	
	private List<String> note;
	
	private String logisticsCompanyName;
	
	private String logisticsCompanyNameText;
	
	private String logisticsNo;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date logisticsDate;

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public List<String> getProductName() {
		return productName;
	}

	public void setProductName(List<String> productName) {
		this.productName = productName;
	}

	public List<String> getBarCode() {
		return barCode;
	}

	public void setBarCode(List<String> barCode) {
		this.barCode = barCode;
	}

	public List<Integer> getCategory() {
		return category;
	}

	public void setCategory(List<Integer> category) {
		this.category = category;
	}

	public List<String> getColor() {
		return color;
	}

	public void setColor(List<String> color) {
		this.color = color;
	}

	public List<Integer> getNumber() {
		return number;
	}

	public void setNumber(List<Integer> number) {
		this.number = number;
	}

	public List<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}

	public List<Double> getVolume() {
		return volume;
	}

	public void setVolume(List<Double> volume) {
		this.volume = volume;
	}

	public List<Double> getWeight() {
		return weight;
	}

	public void setWeight(List<Double> weight) {
		this.weight = weight;
	}

	public List<String> getNote() {
		return note;
	}

	public void setNote(List<String> note) {
		this.note = note;
	}

	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	public String getLogisticsCompanyNameText() {
		return logisticsCompanyNameText;
	}

	public void setLogisticsCompanyNameText(String logisticsCompanyNameText) {
		this.logisticsCompanyNameText = logisticsCompanyNameText;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public Date getLogisticsDate() {
		return logisticsDate;
	}

	public void setLogisticsDate(Date logisticsDate) {
		this.logisticsDate = logisticsDate;
	}

}
