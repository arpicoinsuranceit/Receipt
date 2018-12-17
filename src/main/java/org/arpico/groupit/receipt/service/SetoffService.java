package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;

public interface SetoffService {
	
	//public Integer setoff(InProposalsModel inProposalsModel, String userCode, String locCode, SaveReceiptDto saveReceiptDto, InBillingTransactionsModel deposit, Double recovery) throws Exception;
	
	public List<InBillingTransactionsModel> setoff(InProposalsModel inProposalsModel, String userCode, String locCode, SaveReceiptDto saveReceiptDto, InBillingTransactionsModel deposit, Double hrbamt,ProposalL3Dto autoIssueData,String setoffType) throws Exception;

}
