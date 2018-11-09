package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.dto.CodeTransferHelperDto;

public interface CodeTransferService {
	
	public CodeTransferHelperDto getProposalDetails(String pprNum)throws Exception;
	
	public CodeTransferHelperDto getPolicyDetails(String polNum)throws Exception;
	
	public List<AgentDto> getAllAgents()throws Exception;

}
