package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgnInqAgnListDto;

public interface AgentInquiryService {
	
	List<AgnInqAgnListDto> getAgentListByBranch(String token) throws Exception;
	

}
