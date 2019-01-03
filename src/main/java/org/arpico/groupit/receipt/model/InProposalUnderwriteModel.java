package org.arpico.groupit.receipt.model;

public class InProposalUnderwriteModel {
	private Integer proposalNo;
	private Integer seqNo;
	private Integer polNo;
	private Integer custCode;
	private String custName;
	private String agentCode;
	private String polBranch;
	private String agentBranch;
	private String nic;
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

	public Integer getPolNo() {
		return polNo;
	}

	public void setPolNo(Integer polNo) {
		this.polNo = polNo;
	}

	public Integer getCustCode() {
		return custCode;
	}

	public void setCustCode(Integer custCode) {
		this.custCode = custCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getPolBranch() {
		return polBranch;
	}

	public void setPolBranch(String polBranch) {
		this.polBranch = polBranch;
	}

	public String getAgentBranch() {
		return agentBranch;
	}

	public void setAgentBranch(String agentBranch) {
		this.agentBranch = agentBranch;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "InProposalUnderwriteModel [proposalNo=" + proposalNo + ", seqNo=" + seqNo + ", polNo=" + polNo
				+ ", custCode=" + custCode + ", custName=" + custName + ", agentCode=" + agentCode + ", polBranch="
				+ polBranch + ", agentBranch=" + agentBranch + ", nic=" + nic + ", product=" + product + "]";
	}

}
