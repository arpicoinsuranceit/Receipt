package org.arpico.groupit.receipt.dto;

import java.util.List;

import org.arpico.groupit.receipt.model.InProposalUnderwriteModel;

public class UnderwriteDto {
	
	private List<InProposalUnderwriteModel> inProposalUnderwriteModel;
	private Integer propCount;
	
	public List<InProposalUnderwriteModel> getInProposalUnderwriteModel() {
		return inProposalUnderwriteModel;
	}
	public void setInProposalUnderwriteModel(List<InProposalUnderwriteModel> inProposalUnderwriteModel) {
		this.inProposalUnderwriteModel = inProposalUnderwriteModel;
	}
	public Integer getPropCount() {
		return propCount;
	}
	public void setPropCount(Integer propCount) {
		this.propCount = propCount;
	}
	@Override
	public String toString() {
		return "UnderwriteDto [inProposalUnderwriteModel=" + inProposalUnderwriteModel + ", propCount=" + propCount
				+ "]";
	}
	
	

}
