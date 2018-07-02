package org.arpico.groupit.receipt.model;

public class ProposalNoSeqNoModel {

	private String proposalNo;
	private String seqNo;
	
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	
	@Override
	public String toString() {
		return "ProposalNoSeqNoModel [proposalNo=" + proposalNo + ", seqNo=" + seqNo + "]";
	}
}
