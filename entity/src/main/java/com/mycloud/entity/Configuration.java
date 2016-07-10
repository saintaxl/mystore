/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Shawn
 *
 */
@Entity
@Table(name = "CONFIGURATION")
public class Configuration extends AbstractEntity implements Serializable{
	

	/**
	 * 
	 */
    private static final long serialVersionUID = -2256062816531200046L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="OPERATIVE")
	private Boolean operative;
	
	public Boolean getOperative() {
		return operative;
	}

	public void setOperative(Boolean operative) {
		this.operative = operative;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
