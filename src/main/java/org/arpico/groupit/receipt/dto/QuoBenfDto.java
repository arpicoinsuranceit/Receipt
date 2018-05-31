package org.arpico.groupit.receipt.dto;

public class QuoBenfDto {
	private String benfName;
	private String riderCode;
	private Integer riderTerm;
	private Double riderSum;
	private Double premium;
	public String getBenfName() {
		return benfName;
	}
	public void setBenfName(String benfName) {
		this.benfName = benfName;
	}
	public Double getRiderSum() {
		return riderSum;
	}
	public void setRiderSum(Double riderSum) {
		this.riderSum = riderSum;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public Integer getRiderTerm() {
		return riderTerm;
	}
	public void setRiderTerm(Integer riderTerm) {
		this.riderTerm = riderTerm;
	}
	public String getRiderCode() {
		return riderCode;
	}
	public void setRiderCode(String riderCode) {
		this.riderCode = riderCode;
	}
	
}
