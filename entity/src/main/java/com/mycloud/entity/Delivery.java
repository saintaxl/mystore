/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mycloud.constant.DeliveryStatus;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "DELIVERY")
public class Delivery extends AbstractEntity implements Serializable {

	
    private static final long serialVersionUID = -6287120704265264221L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
    
    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<DeliveryDetails> details;
    
    @Column(name="DELIVERY_NO", nullable=false)
    private String deliveryNo;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    
    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status = DeliveryStatus.SHIPPED;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGISTICS_ID", nullable = false)
    private Logistics logistics;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<DeliveryDetails> getDetails() {
		return details;
	}

	public void setDetails(List<DeliveryDetails> details) {
		this.details = details;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}
    

}
