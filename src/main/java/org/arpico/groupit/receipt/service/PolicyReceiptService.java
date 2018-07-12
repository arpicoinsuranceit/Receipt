package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.springframework.http.ResponseEntity;

public interface PolicyReceiptService {

	List<ProposalNoSeqNoDto> getPolicyNoSeqNoDtoList(String val) throws Exception;

	ProposalBasicDetailsDto getBasicDetails(Integer parseInt, Integer parseInt2) throws Exception;

	ResponseEntity<Object> savePolicyReceipt(SaveReceiptDto saveReceiptDto) throws Exception;
	
	InBillingTransactionsModel createInvoice(InProposalsModel inProposalsModel,
			InBillingTransactionsModel previousInvoice, String user, String loc) throws Exception;
}
