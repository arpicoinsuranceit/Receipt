package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;

public interface QuotationReceiptService {
	
	public ResponseDto saveQuotationReceipt(SaveReceiptDto saveReceiptDto) throws Exception;

	public ProposalBasicDetailsDto getBasicDetails(Integer quoId, Integer seqId) throws Exception;

	public List<String> getBranches(String userCode) throws Exception;

	
}
