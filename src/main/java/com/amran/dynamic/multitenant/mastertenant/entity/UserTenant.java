package com.amran.dynamic.multitenant.mastertenant.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.amran.dynamic.multitenant.mastertenant.userUtils.Role;
import com.amran.dynamic.multitenant.mastertenant.userUtils.StatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class UserTenant {

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tenant_client_id")
	private Integer tenantClientId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "users_id")
	private String userId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "gender")
	private String gender;

	@Column(name = "join_date")
	private LocalDateTime joinDate;

	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "modified")
	private LocalDateTime modified;

	@Column(name = "status", nullable = true)
	private String status = StatusType.Active.getValue();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	@JsonIgnore
	private List<Role> roles;

	public UserTenant(Long id, String firstName, String lastName, String emailId, String mobileNumber, String userId,
			String password, LocalDateTime created, LocalDateTime modified, Integer tenantClientId, String gender,
			LocalDateTime joinDate, String status, List<Role> roles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.userId = userId;
		this.password = password;
		this.gender = gender;
		this.joinDate = joinDate;
		this.status = status;
		this.roles = roles;
		this.tenantClientId = tenantClientId;
		this.created = created;
		this.modified = modified;
	}

	public UserTenant() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTenantClientId() {
		return tenantClientId;
	}

	public void setTenantClientId(Integer tenantClientId) {
		this.tenantClientId = tenantClientId;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
