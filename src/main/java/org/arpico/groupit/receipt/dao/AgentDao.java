package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.AgentModel;

public interface AgentDao {

	List<AgentModel> findAgentLikeAgentCode(Integer agentCode, String sql) throws Exception;

	List<AgentModel> findAgentByCode(String agentCode) throws Exception;

	List<AgentModel> findAgentByCodeAll(String advcod) throws Exception;

	AgentModel findPropAgent(String agentCode)throws Exception;

	List<AgentModel> getAllAgents() throws Exception;

	List<AgentModel> findAgentByLocations(String locCodes) throws Exception;
	
}
