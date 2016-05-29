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
@Table(name = "ROLE")
public class Role extends AbstractEntity implements Serializable{

    private static final long serialVersionUID = 7723485252463719694L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
    
	@Column(name="ROLE",nullable=false , length=100)
    private String role;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

}
