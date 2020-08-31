package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="User")
public class UserEntity {

	@Id
	@Column(name="USER_ID")
	@GenericGenerator(name="candidate_id_gen",strategy = "com.ashokit.generator.SSNCustomGenerator")
	@GeneratedValue(generator="candidate_id_gen")
	private String userId;

	@Column(name="ACCOUNT_STATUS")
	private String status;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAILID",unique = true)
	private String emailId;
	
	@Column(name="PHONE_NO")
	private String phoneNo;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name="CREATED_DATE",updatable = false)
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date createdAt;

	@Column(name="UPDATED_DATE")
	@Temporal(TemporalType.DATE)
	@UpdateTimestamp
	private Date updatedAt;

	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ACTIVE_SW")
	private String activeSW;

	

	public String getActiveSW() {
		return activeSW;
	}

	public void setActiveSW(String activeSW) {
		this.activeSW = activeSW;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}




}
