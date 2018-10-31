package org.arpico.groupit.receipt.model;

public class MisGlItemModel {

	private Integer docNo;
	private String description;
	private Double amount;
	private String remark;
	private Integer interId;
	private String branch;

	public Integer getDocNo() {
		return docNo;
	}

	public void setDocNo(Integer docNo) {
		this.docNo = docNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getInterId() {
		return interId;
	}

	public void setInterId(Integer interId) {
		this.interId = interId;
	}

	@Override
	public String toString() {
		return "MisGlItemModel [docNo=" + docNo + ", description=" + description + ", amount=" + amount + ", remark="
				+ remark + ", interId=" + interId + "]";
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}
