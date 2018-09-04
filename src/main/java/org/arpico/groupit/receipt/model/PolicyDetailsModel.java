package org.arpico.groupit.receipt.model;

import java.util.Date;

public class PolicyDetailsModel {
	
	private String pprnum;
	private String polnum;
	private String polType;
	private String status;
	private Date comDate;
	private String insMonth;
	private Date date;
	private Double amount;
	
	public String getPprnum() {
		return pprnum;
	}
	public void setPprnum(String pprnum) {
		this.pprnum = pprnum;
	}
	public String getPolnum() {
		return polnum;
	}
	public void setPolnum(String polnum) {
		this.polnum = polnum;
	}
	public String getPolType() {
		return polType;
	}
	public void setPolType(String polType) {
		this.polType = polType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getComDate() {
		return comDate;
	}
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	public String getInsMonth() {
		return insMonth;
	}
	public void setInsMonth(String insMonth) {
		this.insMonth = insMonth;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "PolicyDetailsModel [pprnum=" + pprnum + ", polnum=" + polnum + ", polType=" + polType + ", status="
				+ status + ", comDate=" + comDate + ", insMonth=" + insMonth + ", date=" + date + ", amount=" + amount
				+ "]";
	}
	
	

}
