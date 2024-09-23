package com.OffInLieuManagement.entity;

public class OffInLieuRecord {

	private String id;
	private String staffId;
	private String name;
	private String contactNumber;
	private String email;	 
	private String offDate;
	private String satDutyDate;
	private String amPmFull;
	private String approvingOfficerId;
	
	private String dateUpdate;
	private String status;
	
	
	public OffInLieuRecord() {
		super();
	}
	public OffInLieuRecord(String id, String staffId, String name,
			String contactNumber, String email, String satDutyDate,
			String offDate, String amPmFull, String approvingOfficerId,
			String dateUpdate, String status) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.name = name;
		this.contactNumber = contactNumber;
		this.email = email;
		this.satDutyDate = satDutyDate;
		this.offDate = offDate;
		this.amPmFull = amPmFull;
		this.approvingOfficerId = approvingOfficerId;
		this.dateUpdate = dateUpdate;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSatDutyDate() {
		return satDutyDate;
	}
	public void setSatDutyDate(String satDutyDate) {
		this.satDutyDate = satDutyDate;
	}
	public String getOffDate() {
		return offDate;
	}
	public void setOffDate(String offDate) {
		this.offDate = offDate;
	}
	public String getAmPmFull() {
		return amPmFull;
	}
	public void setAmPmFull(String amPmFull) {
		this.amPmFull = amPmFull;
	}
	public String getApprovingOfficerId() {
		return approvingOfficerId;
	}
	public void setApprovingOfficerId(String approvingOfficerId) {
		this.approvingOfficerId = approvingOfficerId;
	}
	public String getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OffInLieuRecord [id=" + id + ", staffId=" + staffId + ", name="
				+ name + ", contactNumber=" + contactNumber + ", email="
				+ email + ", satDutyDate=" + satDutyDate + ", offDate="
				+ offDate + ", amPmFull=" + amPmFull + ", approvingOfficerId="
				+ approvingOfficerId + ", dateUpdate=" + dateUpdate
				+ ", status=" + status + "]";
	}
	

}