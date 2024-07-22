package com.amran.dynamic.multitenant.dto;

import java.time.LocalDateTime;

public class UserDto {

	  private Long id;
	    private String firstName;
	    private String lastName;
	    private String emailId;
	    private Integer tenantClientId;
	    private String mobileNumber;
	    private String userId;
	    private String password;
	    private String gender;
	    private LocalDateTime joinDate;
	    private String status;
	    private String[] roles;
	    private LocalDateTime created;
	    private LocalDateTime modified;

	    public UserDto(Long id, String firstName, String lastName, String emailId, String mobileNumber, String userId,
	                   String password, String gender, Integer tenantClientId, LocalDateTime joinDate, String status, String[] roles,
	                   LocalDateTime created, LocalDateTime modified) {
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
	        this.created = created;
	        this.modified = modified;
	        this.tenantClientId = tenantClientId;
	    }

	    public UserDto() {
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public Integer getTenantClientId() {
			return tenantClientId;
		}

		public void setTenantClientId(Integer tenantClientId) {
			this.tenantClientId = tenantClientId;
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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String[] getRoles() {
			return roles;
		}

		public void setRoles(String[] roles) {
			this.roles = roles;
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

	
}
