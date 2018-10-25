package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class DocumentTypeDto {

	private Integer docTypeId;
	private String docName;
	private boolean active;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	
	public Integer getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
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
	@Override
	public String toString() {
		return "DocumentTypeDto [docTypeId=" + docTypeId + ", docName=" + docName + ", active=" + active
				+ ", createDate=" + createDate + ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy="
				+ modifyBy + "]";
	}
	
	
	
	
}
