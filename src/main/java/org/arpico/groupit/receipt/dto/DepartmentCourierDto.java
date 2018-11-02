package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class DepartmentCourierDto {
	
	private Integer courierDepartmentId;
	private String courierStatus;
	private String receivedBy;
	private Date receivedDate;
	private String createBy;
	private Date createDate;
	private String token;
	
	private Integer courierId;
	private Integer departmentId;
	
	public Integer getCourierDepartmentId() {
		return courierDepartmentId;
	}
	public void setCourierDepartmentId(Integer courierDepartmentId) {
		this.courierDepartmentId = courierDepartmentId;
	}
	public String getCourierStatus() {
		return courierStatus;
	}
	public void setCourierStatus(String courierStatus) {
		this.courierStatus = courierStatus;
	}
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCourierId() {
		return courierId;
	}
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "DepartmentCourierDto [courierDepartmentId=" + courierDepartmentId + ", courierStatus=" + courierStatus
				+ ", receivedBy=" + receivedBy + ", receivedDate=" + receivedDate + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", token=" + token + ", courierId=" + courierId + ", departmentId="
				+ departmentId + "]";
	}
	
	
	

}
