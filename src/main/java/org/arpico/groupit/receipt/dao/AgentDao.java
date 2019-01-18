package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.AgentMasterDetailsModel;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.AgnInqAgnListModel;

public interface AgentDao {

	List<AgentModel> findAgentLikeAgentCode(Integer agentCode, String sql) throws Exception;

	List<AgentModel> findAgentByCode(String agentCode) throws Exception;

	List<AgentModel> findAgentByCodeAll(String advcod) throws Exception;

	AgentModel findPropAgent(String agentCode)throws Exception;

	List<AgentModel> getAllAgents() throws Exception;

	List<AgentModel> findAgentByLocations(String locCodes) throws Exception;

	List<AgnInqAgnListModel> getAgnInqList(String locCodes, Integer page, Integer offset) throws Exception;

	Integer getAgnInqListCount(String locCodes) throws Exception;
	
	AgentMasterDetailsModel getAgentMasterdetails (Integer agnCode) throws Exception;
}
