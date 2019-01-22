package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.dto.SearchDto;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.springframework.http.ResponseEntity;

public interface ProposalServce {

	List<ProposalNoSeqNoDto> getProposalNoSeqNoDtoList(String val) throws Exception;
	
	ProposalNoSeqNoDto getProposalNoSeqNoDto(String pprNo) throws Exception;
	
	ProposalBasicDetailsDto getBasicDetails(Integer pprNum, Integer seqNum) throws Exception;

	ResponseEntity<Object> saveProposal(SaveReceiptDto saveReceiptDto) throws Exception;

	void checkPolicy(InProposalsModel inProposalsModel, Integer pprNo, Integer seqNo, SaveReceiptDto saveReceiptDto,
			String agentCode, String locCode, InBillingTransactionsModel deposit) throws Exception;

	List<SearchDto> getSearch(String value, String type, String receiptType) throws Exception;

	ProposalNoSeqNoDto getPolicyNoSeqNoDto(String string) throws Exception;
	
}
