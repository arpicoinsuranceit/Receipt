package org.arpico.groupit.receipt.dto;

public class RmsDocTxnmGridDto {
	
	private String docCode;
	private Integer docNo;
	private String agentCode;
	private String date;
	private Double amount;

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public Integer getDocNo() {
		return docNo;
	}

	public void setDocNo(Integer docNo) {
		this.docNo = docNo;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "RmsDocTxnmGridDto [docCode=" + docCode + ", docNo=" + docNo + ", agentCode=" + agentCode + ", date="
				+ date + ", amount=" + amount + "]";
	}
	

}
