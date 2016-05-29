/**
 * 
 */
package com.mycloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Shawn
 *
 */

@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {

	@Id
	@Column(name = "SERIES", nullable = false, length = 255)
	private String series;

	@Column(name = "USERNAME", nullable = false, length = 80)
	private String username;

	@Column(name = "TOKEN", nullable = false, length = 100)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_USED",insertable = false, updatable=true, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ")
	private Date lastUsed;
	
	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
