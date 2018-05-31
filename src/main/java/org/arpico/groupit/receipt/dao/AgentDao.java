package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.AgentModel;

public interface AgentDao {

	List<AgentModel> findAgentLikeAgentCode(Integer agentCode) throws Exception;

	List<AgentModel> findAgentByCode(String agentCode);
	
}
