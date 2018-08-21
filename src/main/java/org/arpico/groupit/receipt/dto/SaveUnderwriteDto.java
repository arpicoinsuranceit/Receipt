package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;

public class SaveUnderwriteDto {

	private boolean sendToApprove;
	private Integer proposalNo;
	private Integer seqNo;
	private Integer quotationNo;
	private Integer quotationDetailNo;
	private String token;
	private String propDate;
	private String agentCode;
	private MainLifeUnderwriteDto mainlifeUnderwriteDto;
	private SpouseUnderwriteDto spouseUnderwriteDto;
	private ArrayList<ChildrenDto> children=new ArrayList<>();
	private ArrayList<NomineeUnderwriteDto> nominee=new ArrayList<>();
	
	public boolean getSendToApprove() {
		return sendToApprove;
	}
	public void setSendToApprove(boolean sendToApprove) {
		this.sendToApprove = sendToApprove;
	}
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
	public Integer getQuotationNo() {
		return quotationNo;
	}
	public void setQuotationNo(Integer quotationNo) {
		this.quotationNo = quotationNo;
	}
	public Integer getQuotationDetailNo() {
		return quotationDetailNo;
	}
	public void setQuotationDetailNo(Integer quotationDetailNo) {
		this.quotationDetailNo = quotationDetailNo;
	}
	public SpouseUnderwriteDto getSpouseUnderwriteDto() {
		return spouseUnderwriteDto;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getPropDate() {
		return propDate;
	}
	public void setPropDate(String propDate) {
		this.propDate = propDate;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public void setSpouseUnderwriteDto(SpouseUnderwriteDto spouseUnderwriteDto) {
		this.spouseUnderwriteDto = spouseUnderwriteDto;
	}
	public MainLifeUnderwriteDto getMainlifeUnderwriteDto() {
		return mainlifeUnderwriteDto;
	}
	public void setMainlifeUnderwriteDto(MainLifeUnderwriteDto mainlifeUnderwriteDto) {
		this.mainlifeUnderwriteDto = mainlifeUnderwriteDto;
	}
	public ArrayList<ChildrenDto> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<ChildrenDto> children) {
		this.children = children;
	}
	public ArrayList<NomineeUnderwriteDto> getNominee() {
		return nominee;
	}
	public void setNominee(ArrayList<NomineeUnderwriteDto> nominee) {
		this.nominee = nominee;
	}
	@Override
	public String toString() {
		return "SaveUnderwriteDto [sendToApprove=" + sendToApprove + ", proposalNo=" + proposalNo + ", seqNo=" + seqNo
				+ ", quotationNo=" + quotationNo + ", quotationDetailNo=" + quotationDetailNo + ", token=" + token
				+ ", propDate=" + propDate + ", agentCode=" + agentCode + ", mainlifeUnderwriteDto="
				+ mainlifeUnderwriteDto + ", spouseUnderwriteDto=" + spouseUnderwriteDto + ", children=" + children
				+ ", nominee=" + nominee + "]";
	}
	
	
}
