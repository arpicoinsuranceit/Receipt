package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.springframework.http.ResponseEntity;

public interface ProposalServce {

	List<ProposalNoSeqNoDto> getProposalNoSeqNoDtoList(String val) throws Exception;
	
	ProposalBasicDetailsDto getBasicDetails(Integer pprNum, Integer seqNum) throws Exception;

	ResponseEntity<Object> saveProposal(SaveReceiptDto saveReceiptDto) throws Exception;
	
	
}