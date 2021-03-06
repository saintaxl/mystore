/**
 * 
 */
package com.mycloud.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mycloud.enums.State;

/**
 * @author Shawn
 *
 */
@Entity  
@Table(name="USERS")  
public class User extends AbstractEntity implements Serializable {
	
    private static final long serialVersionUID = -283778350575805260L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name="USERNAME",nullable=false , length=80)
	private String username;
	
    @Column(name="PASSWORD",nullable=false , length=255)
	private String password;
	
    @Column(name="EMAIL",nullable=false , length=100)
	private String email;
	
    @Column(name="MOBILE",nullable=false , length=20)
	private String mobile;
    
    @Column(name="STATE", nullable=false)
    private String state = State.ACTIVE.getState();
    
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToOne(optional = true, mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private UserValidate validate ;
	
	public UserValidate getValidate() {
		return validate;
	}

	public void setValidate(UserValidate validate) {
		this.validate = validate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
