package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;

public interface AgentService {

	List<AgentDto> getAgentList(Integer agentCode) throws Exception;
	
	boolean availableAgent (String agentCode) throws Exception;
}