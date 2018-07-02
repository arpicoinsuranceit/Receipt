package org.arpico.groupit.receipt.dto;

public class ProposalBasicDetailsDto {
	private Integer proposalNo;
	private Integer seqNo;
	private String custName;
	private String custTitle;
	private String agentCode;
	private String product;
	
	public Integer getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(Integer proposalNo) {
		this.proposalNo = proposalNo;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustTitle() {
		return custTitle;
	}
	public void setCustTitle(String custTitle) {
		this.custTitle = custTitle;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ProposalBasicDetailsDto [proposalNo=" + proposalNo + ", seqNo=" + seqNo + ", custName=" + custName
				+ ", custTitle=" + custTitle + ", agentCode=" + agentCode + ", product=" + product + "]";
	}
	

}
