package org.arpico.groupit.receipt.model;

public class PayModeGridModel {

	private Double amount;
	private String day;
	private String month;
	private String year;
	private String payMode;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Override
	public String toString() {
		return "PayModeGridModel [amount=" + amount + ", day=" + day + ", month=" + month + ", year=" + year
				+ ", payMode=" + payMode + "]";
	}

}
