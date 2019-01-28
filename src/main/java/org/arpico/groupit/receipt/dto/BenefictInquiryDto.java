package org.arpico.groupit.receipt.dto;

public class BenefictInquiryDto {
	private String riderCode;
	private String riderName;
	private Integer term;
	private Double sumAssured;
	private Double premium;
	private String type;
	
	public String getRiderCode() {
		return riderCode;
	}
	public void setRiderCode(String riderCode) {
		this.riderCode = riderCode;
	}
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public Double getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "BenefictInquiryDto [riderCode=" + riderCode + ", riderName=" + riderName + ", term=" + term
				+ ", sumAssured=" + sumAssured + ", premium=" + premium + ", type=" + type + "]";
	}
	
	
	
}
