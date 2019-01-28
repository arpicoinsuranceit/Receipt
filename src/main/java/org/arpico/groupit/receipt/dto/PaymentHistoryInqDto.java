package org.arpico.groupit.receipt.dto;

public class PaymentHistoryInqDto {
	private Integer year;
	private Integer month;
	private String date;
	private Double settledAmt;
	private Double dueAmt;
	private String dueDate;
	private Double outstanding;
	private String remark;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getSettledAmt() {
		return settledAmt;
	}

	public void setSettledAmt(Double settledAmt) {
		this.settledAmt = settledAmt;
	}

	public Double getDueAmt() {
		return dueAmt;
	}

	public void setDueAmt(Double dueAmt) {
		this.dueAmt = dueAmt;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PaymentHistoryDto [year=" + year + ", month=" + month + ", date=" + date + ", settledAmt=" + settledAmt
				+ ", dueAmt=" + dueAmt + ", dueDate=" + dueDate + ", outstanding=" + outstanding + ", remark=" + remark
				+ "]";
	}
}
