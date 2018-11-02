package org.arpico.groupit.receipt.dto;

public class CourierDetailsDto {
	
	private String referenceNumber;
	private String polNo;
	private String prpNo;
	private String agentCode;
	private String branch;
	private String underwriterEmail;
	private String department;
	private String docType;
	private String remarks;
	private String status;
	private String user;
	
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getPolNo() {
		return polNo;
	}
	public void setPolNo(String polNo) {
		this.polNo = polNo;
	}
	public String getPrpNo() {
		return prpNo;
	}
	public void setPrpNo(String prpNo) {
		this.prpNo = prpNo;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getUnderwriterEmail() {
		return underwriterEmail;
	}
	public void setUnderwriterEmail(String underwriterEmail) {
		this.underwriterEmail = underwriterEmail;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "CourierDetailsDto [referenceNumber=" + referenceNumber + ", polNo=" + polNo + ", prpNo=" + prpNo
				+ ", agentCode=" + agentCode + ", branch=" + branch + ", underwriterEmail=" + underwriterEmail
				+ ", department=" + department + ", docType=" + docType + ", remarks=" + remarks + ", status=" + status
				+ ", user=" + user + "]";
	}
	
	
	
	
	

}
