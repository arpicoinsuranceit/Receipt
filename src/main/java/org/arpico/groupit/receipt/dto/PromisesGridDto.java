package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class PromisesGridDto {

	private Integer id;
	private String polNum;
	private String pprNum;
	private Date dueDate;
	private Date promiseDate;
	private Double amount;
	private String phoneNum;
	private String custName;
	private String custNic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPolNum() {
		return polNum;
	}

	public void setPolNum(String polNum) {
		this.polNum = polNum;
	}

	public String getPprNum() {
		return pprNum;
	}

	public void setPprNum(String pprNum) {
		this.pprNum = pprNum;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(Date promiseDate) {
		this.promiseDate = promiseDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustNic() {
		return custNic;
	}

	public void setCustNic(String custNic) {
		this.custNic = custNic;
	}

	@Override
	public String toString() {
		return "PromisesGridDto [id=" + id + ", polNum=" + polNum + ", pprNum=" + pprNum + ", dueDate=" + dueDate
				+ ", promiseDate=" + promiseDate + ", amount=" + amount + ", phoneNum=" + phoneNum + ", custName="
				+ custName + ", custNic=" + custNic + "]";
	}
}
