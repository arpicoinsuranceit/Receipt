package org.arpico.groupit.receipt.service;

import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;

public interface SetoffService {
	
	public Integer setoff(InProposalsModel inProposalsModel, String agentCode, String locCode, SaveReceiptDto saveReceiptDto, InBillingTransactionsModel deposit, Double recovery) throws Exception;

}
