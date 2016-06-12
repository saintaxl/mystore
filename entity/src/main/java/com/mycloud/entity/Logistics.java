/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "LOGISTICS")
public class Logistics extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name = "LOGISTICS_NO")
	private String logisticsNo;
	
	@Column(name="NOTE")
    private String note;
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="ARRIVAL_DATE",insertable=true, updatable = false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ")
	private Date arrivalDate;
	
	@OneToMany(mappedBy = "logistics", fetch = FetchType.LAZY)
	private List<Delivery> delivery;
	
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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

	public List<Delivery> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}
	

}
