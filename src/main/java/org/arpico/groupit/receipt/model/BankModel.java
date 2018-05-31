package org.arpico.groupit.receipt.model;

public class BankModel {
	
	private String bankCode;
	private String bankName;
	
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
	
	@Override
	public String toString() {
		return "BankDto [bankCode=" + bankCode + ", bankName=" + bankName + "]";
	}
	

}
