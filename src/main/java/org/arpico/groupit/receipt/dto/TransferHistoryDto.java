package org.arpico.groupit.receipt.dto;

public class TransferHistoryDto {
	private String agentCode;
	private String name;
	private String agentClass;
	private String fromDate;
	private String toDate;
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgentClass() {
		return agentClass;
	}
	public void setAgentClass(String agentClass) {
		this.agentClass = agentClass;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		return "TransferHistoryDto [agentCode=" + agentCode + ", name=" + name + ", agentClass=" + agentClass
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	

}
