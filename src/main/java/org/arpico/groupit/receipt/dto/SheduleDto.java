package org.arpico.groupit.receipt.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SheduleDto implements Serializable{

	
	private Integer policyYear;
	private Integer outYear;
	private Double outSum;
	private Double lorned;
	private Double premiumRate;
	private Double premium;

	
	public SheduleDto() {}
	
	public Integer getPolicyYear() {
		return policyYear;
	}

	public void setPolicyYear(Integer policyYear) {
		this.policyYear = policyYear;
	}

	public Integer getOutYear() {
		return outYear;
	}

	public void setOutYear(Integer outYear) {
		this.outYear = outYear;
	}

	public Double getOutSum() {
		return outSum;
	}

	public void setOutSum(Double outSum) {
		this.outSum = outSum;
	}

	public Double getLorned() {
		return lorned;
	}

	public void setLorned(Double lorned) {
		this.lorned = lorned;
	}

	public Double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}
	
	
}
