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
	
	private String customerName;
	
	private String username;
	
	private String email;
	
	private String mobile;
	
	private String state;
	
	private Set<RoleInfo> roleinfo;
	
	private Date logonTime;
	
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
