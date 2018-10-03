package org.arpico.groupit.receipt.model;

public class AccountGLModel {

	private Integer id;
	private String description;
	private String remark;
	private Double amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "AccountGLDto [id=" + id + ", description=" + description + ", remark=" + remark + ", amount=" + amount
				+ "]";
	}
	
}
