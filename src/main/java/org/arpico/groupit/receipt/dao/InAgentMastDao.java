package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.AgentMastModel;

public interface InAgentMastDao {
	
	List<AgentMastModel> getAgentDetails(String agenCode) throws Exception;

}
