package org.arpico.groupit.receipt.model;

public class BankModel {

	private String bankCode;
	private String bankName;
	private String location;
	private String acccode;

	public String getAcccode() {
		return acccode;
	}

	public void setAcccode(String acccode) {
		this.acccode = acccode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "BankModel [bankCode=" + bankCode + ", bankName=" + bankName + ", location=" + location + "]";
	}

}
