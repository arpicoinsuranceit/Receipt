package org.arpico.groupit.receipt.model;

public class DashboardGridModel {
	
	private String docCode;
	private Integer count;
	private Double amount;
	private String year;
	private String month;
	private String day;

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "DashboardGridModel [docCode=" + docCode + ", count=" + count + ", amount=" + amount + ", year=" + year
				+ ", month=" + month + ", day=" + day + "]";
	}

}
