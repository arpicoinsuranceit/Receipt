package org.arpico.groupit.receipt.dto;

public class SettlementDto {
	private String docnum;
	private String name;
	private Double totPremium;
	private String docCode;
	private String branch;
	private String chqRel;
	private String payMode;

	public String getDocnum() {
		return docnum;
	}

	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotPremium() {
		return totPremium;
	}

	public void setTotPremium(Double totPremium) {
		this.totPremium = totPremium;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getChqRel() {
		return chqRel;
	}

	public void setChqRel(String chqRel) {
		this.chqRel = chqRel;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Override
	public String toString() {
		return "SettlementDto [docnum=" + docnum + ", name=" + name + ", totPremium=" + totPremium + ", docCode="
				+ docCode + ", branch=" + branch + ", chqRel=" + chqRel + ", payMode=" + payMode + "]";
	}
}
