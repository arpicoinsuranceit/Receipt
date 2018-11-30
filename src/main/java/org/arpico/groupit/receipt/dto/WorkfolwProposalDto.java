package org.arpico.groupit.receipt.dto;

import java.util.List;

public class WorkfolwProposalDto {

	private String polNum;
	private String pprNum;
	private String plan;
	private String status;
	private String branch;
	private String agent;
	private String comDate;
	private String expDate;
	private Double bsa;
	private Double totprm;
	private Integer term;
	private String frequancy;

	private WorkflowProposalMainLifeDto mainLifeDto;
	private WorkflowProposalSpouseDto spouseDto;
	private List<WorkflowProposalChildrenDto> childrenDtos;

	private List<WorkflowProposalBenefictDetailDao> benefictDetailsMain;
	private List<WorkflowProposalBenefictDetailDao> benefictDetailsSpouse;
	private List<WorkflowProposalBenefictDetailDao> benefictDetailsChildren;

	public String getPolNum() {
		return polNum;
	}

	public void setPolNum(String polNum) {
		this.polNum = polNum;
	}

	public String getPprNum() {
		return pprNum;
	}

	public void setPprNum(String pprNum) {
		this.pprNum = pprNum;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getComDate() {
		return comDate;
	}

	public void setComDate(String comDate) {
		this.comDate = comDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public Double getBsa() {
		return bsa;
	}

	public void setBsa(Double bsa) {
		this.bsa = bsa;
	}

	public Double getTotprm() {
		return totprm;
	}

	public void setTotprm(Double totprm) {
		this.totprm = totprm;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getFrequancy() {
		return frequancy;
	}

	public void setFrequancy(String frequancy) {
		this.frequancy = frequancy;
	}

	public WorkflowProposalMainLifeDto getMainLifeDto() {
		return mainLifeDto;
	}

	public void setMainLifeDto(WorkflowProposalMainLifeDto mainLifeDto) {
		this.mainLifeDto = mainLifeDto;
	}

	public WorkflowProposalSpouseDto getSpouseDto() {
		return spouseDto;
	}

	public void setSpouseDto(WorkflowProposalSpouseDto spouseDto) {
		this.spouseDto = spouseDto;
	}

	public List<WorkflowProposalChildrenDto> getChildrenDtos() {
		return childrenDtos;
	}

	public void setChildrenDtos(List<WorkflowProposalChildrenDto> childrenDtos) {
		this.childrenDtos = childrenDtos;
	}

	public List<WorkflowProposalBenefictDetailDao> getBenefictDetailsMain() {
		return benefictDetailsMain;
	}

	public void setBenefictDetailsMain(List<WorkflowProposalBenefictDetailDao> benefictDetailsMain) {
		this.benefictDetailsMain = benefictDetailsMain;
	}

	public List<WorkflowProposalBenefictDetailDao> getBenefictDetailsSpouse() {
		return benefictDetailsSpouse;
	}

	public void setBenefictDetailsSpouse(List<WorkflowProposalBenefictDetailDao> benefictDetailsSpouse) {
		this.benefictDetailsSpouse = benefictDetailsSpouse;
	}

	public List<WorkflowProposalBenefictDetailDao> getBenefictDetailsChildren() {
		return benefictDetailsChildren;
	}

	public void setBenefictDetailsChildren(List<WorkflowProposalBenefictDetailDao> benefictDetailsChildren) {
		this.benefictDetailsChildren = benefictDetailsChildren;
	}

	@Override
	public String toString() {
		return "WorkfolwProposalDto [polNum=" + polNum + ", pprNum=" + pprNum + ", plan=" + plan + ", status=" + status
				+ ", branch=" + branch + ", agent=" + agent + ", comDate=" + comDate + ", expDate=" + expDate + ", bsa="
				+ bsa + ", totprm=" + totprm + ", term=" + term + ", frequancy=" + frequancy + ", mainLifeDto="
				+ mainLifeDto + ", spouseDto=" + spouseDto + ", childrenDtos=" + childrenDtos + ", benefictDetailsMain="
				+ benefictDetailsMain + ", benefictDetailsSpouse=" + benefictDetailsSpouse
				+ ", benefictDetailsChildren=" + benefictDetailsChildren + "]";
	}

}
