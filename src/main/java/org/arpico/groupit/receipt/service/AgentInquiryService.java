package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentMasterDto;
import org.arpico.groupit.receipt.dto.AgnInqAgnListDto;

public interface AgentInquiryService {
	
	List<AgnInqAgnListDto> getAgentListByBranch(String token, Integer page, Integer offset,String equality,String column,String data) throws Exception;

	Integer getAgentListByBranchCountLength(String token,String equality,String column,String data) throws Exception;
	
	AgentMasterDto getAgentFullDetails(String agentCode)throws Exception;
	

}
