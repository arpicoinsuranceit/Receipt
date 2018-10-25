package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class SubDepartmentDocumentDto {
	
	private Integer subDepDocId;
	private boolean active;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	
	private Integer subDepartmentId;
	private Integer documentTypeId;
	
	public Integer getSubDepDocId() {
		return subDepDocId;
	}
	public void setSubDepDocId(Integer subDepDocId) {
		this.subDepDocId = subDepDocId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	public Integer getSubDepartmentId() {
		return subDepartmentId;
	}
	public void setSubDepartmentId(Integer subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}
	public Integer getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(Integer documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	@Override
	public String toString() {
		return "SubDepartmentDocumentDto [subDepDocId=" + subDepDocId + ", active=" + active + ", createDate="
				+ createDate + ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy
				+ ", subDepartmentId=" + subDepartmentId + ", documentTypeId=" + documentTypeId + "]";
	}
	
	

}
