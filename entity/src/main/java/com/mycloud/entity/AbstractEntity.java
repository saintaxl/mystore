/**
 * 
 */
package com.mycloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.DateTime;

/**
 * @author Shawn
 *
 */
@MappedSuperclass
public class AbstractEntity {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE",insertable=true, updatable = false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ")
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_DATE",insertable = false, updatable=true ,columnDefinition="TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ")
	private Date updateDate;
	
	@Version
    @Column(name = "LOCK_VERSION", length=1000, columnDefinition = "BIGINT NOT NULL DEFAULT 1")
    private Long lockVersion;
	
	public Long getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@PrePersist
    void onCreate() {
        this.setCreateDate(new DateTime().toDate());
    }

    @PreUpdate
    void onPersist() {
        this.setUpdateDate(new DateTime().toDate());
    }
	
}
