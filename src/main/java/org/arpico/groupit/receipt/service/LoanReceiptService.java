package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.springframework.http.ResponseEntity;

public interface LoanReceiptService {

	List<Integer> findLoanNoByPolnum(String polnum)throws Exception;

	ProposalBasicDetailsDto getBasicDetails(Integer polNo, Integer seqNo) throws Exception;

	ResponseEntity<Object> saveLoanReceipt(SaveReceiptDto saveReceiptDto) throws Exception;
	
}
