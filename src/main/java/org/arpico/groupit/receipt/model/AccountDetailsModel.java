package org.arpico.groupit.receipt.model;

public class AccountDetailsModel {
	
	private String branch;
	private String accNO;
	private String description;
	private Double dr;
	private Double cr;
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccNO() {
		return accNO;
	}
	public void setAccNO(String accNO) {
		this.accNO = accNO;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDr() {
		return dr;
	}
	public void setDr(Double dr) {
		this.dr = dr;
	}
	public Double getCr() {
		return cr;
	}
	public void setCr(Double cr) {
		this.cr = cr;
	}
	@Override
	public String toString() {
		return "AccountDetailsModel [branch=" + branch + ", accNO=" + accNO + ", description=" + description + ", dr="
				+ dr + ", cr=" + cr + "]";
	}
	
	

}
