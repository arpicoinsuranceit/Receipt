package org.arpico.groupit.receipt.dto;

public class SaveReceiptDto {

	private Integer quotationId;
	private Integer quotationDetailId; 
	private String bankCode;
	private String remark; 
	private String payMode;
	private Integer amount;
	private String payAmountWord;
	private String productCode;
	private String branchCode;
	private String agentCode;
	
	public Integer getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}
	public Integer getQuotationDetailId() {
		return quotationDetailId;
	}
	public void setQuotationDetailId(Integer quotationDetailId) {
		this.quotationDetailId = quotationDetailId;
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
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getPayAmountWord() {
		return payAmountWord;
	}
	public void setPayAmountWord(String payAmountWord) {
		this.payAmountWord = payAmountWord;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	@Override
	public String toString() {
		return "SaveReceiptDto [quotationId=" + quotationId + ", quotationDetailId=" + quotationDetailId + ", bankCode="
				+ bankCode + ", remark=" + remark + ", payMode=" + payMode + ", amount=" + amount + ", payAmountWord="
				+ payAmountWord + ", productCode=" + productCode + ", branchCode=" + branchCode + ", agentCode="
				+ agentCode + "]";
	}
	
}
