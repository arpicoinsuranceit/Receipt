package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class RefTypeDto {
	
	private Integer refTypeId;
	private String refTypeName;
	private String creBy;
	private Date creDate;
	public Integer getRefTypeId() {
		return refTypeId;
	}
	public void setRefTypeId(Integer refTypeId) {
		this.refTypeId = refTypeId;
	}
	public String getRefTypeName() {
		return refTypeName;
	}
	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}
	public String getCreBy() {
		return creBy;
	}
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}
	public Date getCreDate() {
		return creDate;
	}
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}
	@Override
	public String toString() {
		return "RefTypeDto [refTypeId=" + refTypeId + ", refTypeName=" + refTypeName + ", creBy=" + creBy + ", creDate="
				+ creDate + "]";
	}
	
	

}
