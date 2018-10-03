package org.arpico.groupit.receipt.dto;

import java.util.List;

public class MiscellaneousReceiptInvDto {

	private String branch;
	private String bank;
	private String agent;
	private String remark;
	private String paymode;
	private String chqNo;
	private String chqDate;
	private String chqBank;
	private Double amount;
	private String amtInWord;
	private List<ExpenseDto> expencess;
	private List<AccountGLDto> accounts;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getChqNo() {
		return chqNo;
	}

	public void setChqNo(String chqNo) {
		this.chqNo = chqNo;
	}

	public String getChqDate() {
		return chqDate;
	}

	public void setChqDate(String chqDate) {
		this.chqDate = chqDate;
	}

	public String getChqBank() {
		return chqBank;
	}

	public void setChqBank(String chqBank) {
		this.chqBank = chqBank;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAmtInWord() {
		return amtInWord;
	}

	public void setAmtInWord(String amtInWord) {
		this.amtInWord = amtInWord;
	}

	public List<ExpenseDto> getExpencess() {
		return expencess;
	}

	public void setExpencess(List<ExpenseDto> expencess) {
		this.expencess = expencess;
	}

	public List<AccountGLDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountGLDto> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "MiscellaneousReceiptInvDto [branch=" + branch + ", bank=" + bank + ", agent=" + agent + ", remark="
				+ remark + ", paymode=" + paymode + ", chqNo=" + chqNo + ", chqDate=" + chqDate + ", chqBank=" + chqBank
				+ ", amount=" + amount + ", amtInWord=" + amtInWord + ", expencess=" + expencess + ", accounts="
				+ accounts + "]";
	}

}
