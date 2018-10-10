package org.arpico.groupit.receipt.model;

public class DashboardCashFlowSummeryModel {

	private Double amount;
	private Integer count;
	private String docCode;
	private String payMode;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Override
	public String toString() {
		return "DashboardCashFlowSummeryModel [amount=" + amount + ", count=" + count + ", docCode=" + docCode
				+ ", payMode=" + payMode + "]";
	}

}
