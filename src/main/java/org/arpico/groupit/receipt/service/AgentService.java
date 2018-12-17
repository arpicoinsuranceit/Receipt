
package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;

public interface AgentService {

	List<AgentDto> getAgentList(Integer agentCode, String token) throws Exception;
	
	boolean availableAgent (String agentCode) throws Exception;

	List<AgentDto> findAgentByLocations(String loccodes) throws Exception;

	AgentDto getAgentDetails(String agentCode) throws Exception;
}
