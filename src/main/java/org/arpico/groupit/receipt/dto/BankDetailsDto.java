package org.arpico.groupit.receipt.dto;

public class BankDetailsDto {
	
	private String insType; 
	private String colBank; 
	private String insNo; 
	private String branchCode;
	private String insDate; 
	private Double amount;
	private String status;
	private String remarks;
	
	public String getInsType() {
		return insType;
	}
	public void setInsType(String insType) {
		this.insType = insType;
	}
	public String getColBank() {
		return colBank;
	}
	public void setColBank(String colBank) {
		this.colBank = colBank;
	}
	public String getInsNo() {
		return insNo;
	}
	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "BankDetailsDto [insType=" + insType + ", colBank=" + colBank + ", insNo=" + insNo + ", branchCode="
				+ branchCode + ", insDate=" + insDate + ", amount=" + amount + ", status=" + status + ", remarks="
				+ remarks + "]";
	}
	
	

}
