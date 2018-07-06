package org.arpico.groupit.receipt.dto;

public class SaveReceiptDto {

	private Integer quotationId;
	private Integer quotationDetailId; 
	private Integer propId;
	private Integer propSeq; 
	private Integer polId;
	private Integer polSeq; 
	private String bankCode;
	private String remark; 
	private String payMode;
	private Double amount;
	private String payAmountWord;
	private String productCode;
	private String branchCode;
	private String agentCode;
	private String chequeno;
	private String chequedate;
	private String chequebank;
	private String transferno;
	private String token;
	
	
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
	public Integer getPropId() {
		return propId;
	}
	public void setPropId(Integer propId) {
		this.propId = propId;
	}
	public Integer getPropSeq() {
		return propSeq;
	}
	public void setPropSeq(Integer propSeq) {
		this.propSeq = propSeq;
	}
	public Integer getPolId() {
		return polId;
	}
	public void setPolId(Integer polId) {
		this.polId = polId;
	}
	public Integer getPolSeq() {
		return polSeq;
	}
	public void setPolSeq(Integer polSeq) {
		this.polSeq = polSeq;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
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
	public String getChequeno() {
		return chequeno;
	}
	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}
	public String getChequedate() {
		return chequedate;
	}
	public void setChequedate(String chequedate) {
		this.chequedate = chequedate;
	}
	public String getChequebank() {
		return chequebank;
	}
	public void setChequebank(String chequebank) {
		this.chequebank = chequebank;
	}
	public String getTransferno() {
		return transferno;
	}
	public void setTransferno(String transferno) {
		this.transferno = transferno;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "SaveReceiptDto [quotationId=" + quotationId + ", quotationDetailId=" + quotationDetailId + ", propId="
				+ propId + ", propSeq=" + propSeq + ", polId=" + polId + ", polSeq=" + polSeq + ", bankCode=" + bankCode
				+ ", remark=" + remark + ", payMode=" + payMode + ", amount=" + amount + ", payAmountWord="
				+ payAmountWord + ", productCode=" + productCode + ", branchCode=" + branchCode + ", agentCode="
				+ agentCode + ", chequeno=" + chequeno + ", chequedate=" + chequedate + ", chequebank=" + chequebank
				+ ", transferno=" + transferno + ", token=" + token + "]";
	}
	
	
}
