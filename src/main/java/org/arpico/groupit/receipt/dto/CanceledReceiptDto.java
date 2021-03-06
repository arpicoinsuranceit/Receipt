package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class CanceledReceiptDto {
	
	private String sbuCode;
	private String locCode;
	private String receiptNo;
	private String polNum;
	private String pprNum;
	private String reason;
	private String status;
	private String requestBy;
	private Date requestDate;
	private Double amount;
	private String docCode;
	private String approvedBy;
	private Date approvedDate;
	private String gmRemark;
	
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
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
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
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
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
	
	public String getGmRemark() {
		return gmRemark;
	}
	public void setGmRemark(String gmRemark) {
		this.gmRemark = gmRemark;
	}
	@Override
	public String toString() {
		return "CanceledReceiptDto [sbuCode=" + sbuCode + ", locCode=" + locCode + ", receiptNo=" + receiptNo
				+ ", polNum=" + polNum + ", pprNum=" + pprNum + ", reason=" + reason + ", status=" + status
				+ ", requestBy=" + requestBy + ", requestDate=" + requestDate + ", amount=" + amount + ", docCode="
				+ docCode + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", gmRemark=" + gmRemark
				+ "]";
	}
	
	
}
