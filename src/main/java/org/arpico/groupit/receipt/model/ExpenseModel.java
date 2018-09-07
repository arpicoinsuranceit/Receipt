package org.arpico.groupit.receipt.model;

public class ExpenseModel {

	private String expenceId;
	private String description;
	private Double amount;

	public String getExpenceId() {
		return expenceId;
	}

	public void setExpenceId(String expenceId) {
		this.expenceId = expenceId;
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

	@Override
	public String toString() {
		return "ExpenseModel [expenceId=" + expenceId + ", description=" + description + ", amount=" + amount + "]";
	}

}
