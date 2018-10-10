package org.arpico.groupit.receipt.model;

import java.util.Date;

public class DashboardDetailsModel {

	private String docCode;
	private Integer docNumber;
	private String remark;
	private Double amount;
	private Date createDate;

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public Integer getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(Integer docNumber) {
		this.docNumber = docNumber;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "DashboardDetailsModel [docCode=" + docCode + ", docNumber=" + docNumber + ", remark=" + remark
				+ ", amount=" + amount + ", createDate=" + createDate + "]";
	}

}
