package org.arpico.groupit.receipt.dto;


public class PolicyDetailsDto {
	private String pprnum;
	private String polnum;
	private String polType;
	private String status;
	private String comDate;
	private String insMonth;
	private String date;
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
	public String getComDate() {
		return comDate;
	}
	public void setComDate(String comDate) {
		this.comDate = comDate;
	}
	public String getInsMonth() {
		return insMonth;
	}
	public void setInsMonth(String insMonth) {
		this.insMonth = insMonth;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
