package org.arpico.groupit.receipt.dto;

public class TargetsDto {
	
	private String month;
	private Double targetAmount;
	private Double premium;
	private Double orRate;
	private Double cfAmount;
	private Double achAmount;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public Double getOrRate() {
		return orRate;
	}
	public void setOrRate(Double orRate) {
		this.orRate = orRate;
	}
	public Double getCfAmount() {
		return cfAmount;
	}
	public void setCfAmount(Double cfAmount) {
		this.cfAmount = cfAmount;
	}
	public Double getAchAmount() {
		return achAmount;
	}
	public void setAchAmount(Double achAmount) {
		this.achAmount = achAmount;
	}
	
	

}
