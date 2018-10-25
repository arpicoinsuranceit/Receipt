package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class SubDepartmentDocumentCourierHelperDto {
	private Integer subDepartmentDocumentCourierId;
	private String subDepDocCouToken;
	private String underwriterEmail;
	private String remark;
	private String status;
	private String referenceNo;
	private String referenceType;
	private String branchCode;
	private Date createDate;
	private String createBy;
	private String documentType;
	private String rcvdBy;
	private Date rcvdDate;
	
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
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	public String getRcvdBy() {
		return rcvdBy;
	}
	public void setRcvdBy(String rcvdBy) {
		this.rcvdBy = rcvdBy;
	}
	public Date getRcvdDate() {
		return rcvdDate;
	}
	public void setRcvdDate(Date rcvdDate) {
		this.rcvdDate = rcvdDate;
	}
	
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	@Override
	public String toString() {
		return "SubDepartmentDocumentCourierHelperDto [subDepartmentDocumentCourierId=" + subDepartmentDocumentCourierId
				+ ", subDepDocCouToken=" + subDepDocCouToken + ", underwriterEmail=" + underwriterEmail + ", remark="
				+ remark + ", status=" + status + ", referenceNo=" + referenceNo + ", referenceType=" + referenceType
				+ ", branchCode=" + branchCode + ", createDate=" + createDate + ", createBy=" + createBy
				+ ", documentType=" + documentType + ", rcvdBy=" + rcvdBy + ", rcvdDate=" + rcvdDate + "]";
	}
	
	
}
