package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class CodeTransferDto {
	
	private Integer codeTransferId;
	private String sbuCode;
	private String locCode;
	private String polNum;
	private String pprNum;
	private String reason;
	private String status;
	private String requestBy;
	private Date requestDate;
	private String approvedBy;
	private Date approvedDate;
	private Date createDate;
	private String createBy;
	private String modifyBy;
	private Date modifyDate;
	private String approverRemark;
	private String oldAgentCode;
	private String newAgentCode;
	
	public Integer getCodeTransferId() {
		return codeTransferId;
	}
	public void setCodeTransferId(Integer codeTransferId) {
		this.codeTransferId = codeTransferId;
	}
	public String getSbuCode() {
		return sbuCode;
	}
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}
	public String getLocCode() {
		return locCode;
	}
	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}
	public String getPolNum() {
		return polNum;
	}
	public void setPolNum(String polNum) {
		this.polNum = polNum;
	}
	public String getPprNum() {
		return pprNum;
	}
	public void setPprNum(String pprNum) {
		this.pprNum = pprNum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getApproverRemark() {
		return approverRemark;
	}
	public void setApproverRemark(String approverRemark) {
		this.approverRemark = approverRemark;
	}
	public String getOldAgentCode() {
		return oldAgentCode;
	}
	public void setOldAgentCode(String oldAgentCode) {
		this.oldAgentCode = oldAgentCode;
	}
	public String getNewAgentCode() {
		return newAgentCode;
	}
	public void setNewAgentCode(String newAgentCode) {
		this.newAgentCode = newAgentCode;
	}
	@Override
	public String toString() {
		return "CodeTransferDto [codeTransferId=" + codeTransferId + ", sbuCode=" + sbuCode + ", locCode=" + locCode
				+ ", polNum=" + polNum + ", pprNum=" + pprNum + ", reason=" + reason + ", status=" + status
				+ ", requestBy=" + requestBy + ", requestDate=" + requestDate + ", approvedBy=" + approvedBy
				+ ", approvedDate=" + approvedDate + ", createDate=" + createDate + ", createBy=" + createBy
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", approverRemark=" + approverRemark
				+ ", oldAgentCode=" + oldAgentCode + ", newAgentCode=" + newAgentCode + "]";
	}
	
	

}
