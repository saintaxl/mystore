/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "INVENTORY")
public class Inventory extends AbstractEntity implements Serializable {

	
    private static final long serialVersionUID = -6287120704265264221L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUANTITY_ID")
    private Quantity quantity;
    
    @Column(name="BAR_CODE")
    private String barCode;
    
    @Column(name="PRODUCT_NAME")
    private String productName;
    
    @Column(name="COLOR")
    private String color;
    
    @Column(name="SHELVES_NO")
    private String shelvesNo;
    
    @Column(name="NUMBER")
    private Integer number;
    
    @Column(name="VOLUME")
    private Double volume;
    
    @Column(name="WEIGHT")
    private Double weight;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;
    
    @Column(name="MD5")
    private String md5;
    
	public String getShelvesNo() {
		return shelvesNo;
	}

	public void setShelvesNo(String shelvesNo) {
		this.shelvesNo = shelvesNo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public void setQuantity(Quantity quantity) {
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

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
	

}
