package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class CourierDto {
	
	private Integer courierId;
	private String token;
	private String branchCode;
	private String courierStatus;
	private String remark;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	
	public Integer getCourierId() {
		return courierId;
	}
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getCourierStatus() {
		return courierStatus;
	}
	public void setCourierStatus(String courierStatus) {
		this.courierStatus = courierStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	@Override
	public String toString() {
		return "CourierDto [courierId=" + courierId + ", token=" + token + ", branchCode=" + branchCode + ", remark="
				+ remark + ", createDate=" + createDate + ", createBy=" + createBy + ", modifyDate=" + modifyDate
				+ ", modifyBy=" + modifyBy + "]";
	}
	
	

}
