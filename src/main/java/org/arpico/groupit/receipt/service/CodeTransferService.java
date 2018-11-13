package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.dto.CodeTransferHelperDto;
import org.springframework.http.ResponseEntity;

public interface CodeTransferService {
	
	public ResponseEntity<Object> getProposalDetails(String pprNum)throws Exception;
	
	public ResponseEntity<Object> getPolicyDetails(String polNum)throws Exception;
	
	public List<AgentDto> getAllAgents()throws Exception;

}
