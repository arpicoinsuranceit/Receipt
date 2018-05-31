package org.arpico.groupit.receipt.dto;

public class ReceiptDto {
	
	private String no;
	private String agentCode;
	private String bankCode;
	private String remark;
	private Double amount;
	private String amountInWord;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAmountInWord() {
		return amountInWord;
	}
	public void setAmountInWord(String amountInWord) {
		this.amountInWord = amountInWord;
	}
	
	@Override
	public String toString() {
		return "ReceiptDto [no=" + no + ", agentCode=" + agentCode + ", bankCode=" + bankCode + ", remark=" + remark
				+ ", amount=" + amount + ", amountInWord=" + amountInWord + "]";
	}

}
