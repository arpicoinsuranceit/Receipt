package org.arpico.groupit.receipt.dto;

import java.util.List;

public class ProposalBasicDetailsDto {
	private Integer proposalNo;
	private Integer seqNo;
	private String custName;
	private String custTitle;
	private String agentCode;
	private String agentName;
	private String location;
	private String product;
	private Double premium;
	private Double amtPayble;
	private String status;
	private String mobile;
	private Integer id2;

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	private List<LastReceiptSummeryDto> lastReceiptSummeryDtos;

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

	public List<LastReceiptSummeryDto> getLastReceiptSummeryDtos() {
		return lastReceiptSummeryDtos;
	}

	public void setLastReceiptSummeryDtos(List<LastReceiptSummeryDto> lastReceiptSummeryDtos) {
		this.lastReceiptSummeryDtos = lastReceiptSummeryDtos;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Double getAmtPayble() {
		return amtPayble;
	}

	public void setAmtPayble(Double amtPayble) {
		this.amtPayble = amtPayble;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ProposalBasicDetailsDto [proposalNo=" + proposalNo + ", seqNo=" + seqNo + ", custName=" + custName
				+ ", custTitle=" + custTitle + ", agentCode=" + agentCode + ", product=" + product + ", premium="
				+ premium + ", amtPayble=" + amtPayble + ", status=" + status + ", mobile=" + mobile + ", id2=" + id2
				+ ", lastReceiptSummeryDtos=" + lastReceiptSummeryDtos + "]";
	}

}
