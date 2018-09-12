package org.arpico.groupit.receipt.dto;

public class ExpenseDto {

	private String expenseId;
	private String description;
	private Double amount;
	private String remark;
	private Integer qty;

	public String getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "ExpenseDto [expenseId=" + expenseId + ", description=" + description + ", amount=" + amount
				+ ", remark=" + remark + ", qty=" + qty + "]";
	}

}
