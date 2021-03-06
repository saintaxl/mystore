/**
 * 
 */
package com.mycloud.store.sso;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Shawn
 *
 */
public class UserInfo implements Serializable{
	
    private static final long serialVersionUID = 2237462955078339901L;

	private String customerName;
	
	private String username;
	
	private String email;
	
	private String mobile;
	
	private String state;
	
	private String customerNo; 
	
	private String customerAcronym;
	
	private Set<RoleInfo> roleinfo;
	
	private Date logonTime;
	
	public String getCustomerAcronym() {
		return customerAcronym;
	}

	public void setCustomerAcronym(String customerAcronym) {
		this.customerAcronym = customerAcronym;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public Date getLogonTime() {
		return logonTime;
	}

	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<RoleInfo> getRoleinfo() {
		return roleinfo;
	}

	public void setRoleinfo(Set<RoleInfo> roleinfo) {
		this.roleinfo = roleinfo;
	}

	
}
