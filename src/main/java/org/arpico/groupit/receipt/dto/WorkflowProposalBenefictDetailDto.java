package org.arpico.groupit.receipt.dto;

public class WorkflowProposalBenefictDetailDto {

	private String benefictCode;
	private String benefictNAme;
	private Double sumassured;
	private Double premium;

	public String getBenefictCode() {
		return benefictCode;
	}

	public void setBenefictCode(String benefictCode) {
		this.benefictCode = benefictCode;
	}

	public String getBenefictNAme() {
		return benefictNAme;
	}

	public void setBenefictNAme(String benefictNAme) {
		this.benefictNAme = benefictNAme;
	}

	public Double getSumassured() {
		return sumassured;
	}

	public void setSumassured(Double sumassured) {
		this.sumassured = sumassured;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "WorkflowProposalBenefictDetail [benefictCode=" + benefictCode + ", benefictNAme=" + benefictNAme
				+ ", sumassured=" + sumassured + ", premium=" + premium + "]";
	}

}
