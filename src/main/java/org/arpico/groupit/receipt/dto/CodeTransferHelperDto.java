package org.arpico.groupit.receipt.dto;

public class CodeTransferHelperDto {
	
	private String pprNum;
	private String branch;
	private String agentCode;
	private String agentName;
	private String designation;
	
	public String getPprNum() {
		return pprNum;
	}
	public void setPprNum(String pprNum) {
		this.pprNum = pprNum;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "CodeTransferHelperDto [pprNum=" + pprNum + ", branch=" + branch + ", agentCode=" + agentCode
				+ ", agentName=" + agentName + ", designation=" + designation + "]";
	}
	
	

}
