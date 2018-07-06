package org.arpico.groupit.receipt.service;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;

public interface QuotationReceiptService {
	
	public String saveQuotationReceipt(SaveReceiptDto saveReceiptDto) throws Exception;

	public ProposalBasicDetailsDto getBasicDetails(Integer quoId, Integer seqId) throws Exception;
	
}
