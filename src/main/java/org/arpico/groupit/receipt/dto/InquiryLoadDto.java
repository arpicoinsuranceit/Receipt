package org.arpico.groupit.receipt.dto;

public class InquiryLoadDto {

	private String proposalNo;
	private String policyNo;
	private String mainLifeName;
	private String nic;
	private String product;
	private String proposalStatus;
	private String advisorCode;

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getMainLifeName() {
		return mainLifeName;
	}

	public void setMainLifeName(String mainLifeName) {
		this.mainLifeName = mainLifeName;
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

	public String getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public String getAdvisorCode() {
		return advisorCode;
	}

	public void setAdvisorCode(String advisorCode) {
		this.advisorCode = advisorCode;
	}

	@Override
	public String toString() {
		return "InquiryLoadDto [proposalNo=" + proposalNo + ", policyNo=" + policyNo + ", mainLifeName=" + mainLifeName
				+ ", nic=" + nic + ", product=" + product + ", proposalStatus=" + proposalStatus + ", advisorCode="
				+ advisorCode + "]";
	}

}
