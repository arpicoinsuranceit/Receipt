package org.arpico.groupit.receipt.dto;

public class CodeTransferGridDto {

	private String no;
	private String crAgn;
	private String crBranch;
	private String newAgn;
	private String newBranch;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCrAgn() {
		return crAgn;
	}

	public void setCrAgn(String crAgn) {
		this.crAgn = crAgn;
	}

	public String getCrBranch() {
		return crBranch;
	}

	public void setCrBranch(String crBranch) {
		this.crBranch = crBranch;
	}

	public String getNewAgn() {
		return newAgn;
	}

	public void setNewAgn(String newAgn) {
		this.newAgn = newAgn;
	}

	public String getNewBranch() {
		return newBranch;
	}

	public void setNewBranch(String newBranch) {
		this.newBranch = newBranch;
	}

	@Override
	public String toString() {
		return "CodeTransferGridDto [no=" + no + ", crAgn=" + crAgn + ", crBranch=" + crBranch + ", newAgn=" + newAgn
				+ ", newBranch=" + newBranch + "]";
	}

}
