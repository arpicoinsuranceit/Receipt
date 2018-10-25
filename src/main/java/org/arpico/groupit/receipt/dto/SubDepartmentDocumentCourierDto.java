package org.arpico.groupit.receipt.dto;

import java.util.Date;


public class SubDepartmentDocumentCourierDto {
	
	private Integer subDepartmentDocumentCourierId;
	private String subDepDocCouToken;
	private String underwriterEmail;
	private String remark;
	private String status;
	private String receivedBy;
	private Date receivedDate;
	private String referenceNo;
	private String referenceType;
	private String branchCode;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	private String currentUser;
	
	private Integer subDepartmentDocumentId;
	private Integer departmentCourierId;
	
	public Integer getSubDepartmentDocumentCourierId() {
		return subDepartmentDocumentCourierId;
	}
	public void setSubDepartmentDocumentCourierId(Integer subDepartmentDocumentCourierId) {
		this.subDepartmentDocumentCourierId = subDepartmentDocumentCourierId;
	}
	public String getSubDepDocCouToken() {
		return subDepDocCouToken;
	}
	public void setSubDepDocCouToken(String subDepDocCouToken) {
		this.subDepDocCouToken = subDepDocCouToken;
	}
	public String getUnderwriterEmail() {
		return underwriterEmail;
	}
	public void setUnderwriterEmail(String underwriterEmail) {
		this.underwriterEmail = underwriterEmail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public Integer getSubDepartmentDocumentId() {
		return subDepartmentDocumentId;
	}
	public void setSubDepartmentDocumentId(Integer subDepartmentDocumentId) {
		this.subDepartmentDocumentId = subDepartmentDocumentId;
	}
	public Integer getDepartmentCourierId() {
		return departmentCourierId;
	}
	public void setDepartmentCourierId(Integer departmentCourierId) {
		this.departmentCourierId = departmentCourierId;
	}
	
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	@Override
	public String toString() {
		return "SubDepartmentDocumentCourierDto [subDepartmentDocumentCourierId=" + subDepartmentDocumentCourierId
				+ ", subDepDocCouToken=" + subDepDocCouToken + ", underwriterEmail=" + underwriterEmail + ", remark="
				+ remark + ", status=" + status + ", receivedBy=" + receivedBy + ", receivedDate=" + receivedDate
				+ ", referenceNo=" + referenceNo + ", referenceType=" + referenceType + ", branchCode=" + branchCode
				+ ", createDate=" + createDate + ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy="
				+ modifyBy + ", currentUser=" + currentUser + ", subDepartmentDocumentId=" + subDepartmentDocumentId
				+ ", departmentCourierId=" + departmentCourierId + "]";
	}
	
	
	

}
