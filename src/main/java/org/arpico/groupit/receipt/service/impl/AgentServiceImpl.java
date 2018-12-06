package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgentServiceImpl implements AgentService{

	@Autowired
	private AgentDao agentDao;
	
	@Override
	public List<AgentDto> getAgentList(Integer agentCode) throws Exception {
		List<AgentModel> agentModels = agentDao.findAgentLikeAgentCode(agentCode);
		List<AgentDto> agentDtos = new ArrayList<>();
		for (AgentModel agentModel : agentModels) {
			AgentDto agentDto = getAgent(agentModel);
			agentDtos.add(agentDto);
		}
		return agentDtos;
	}

	private AgentDto getAgent(AgentModel agentModel) {
		AgentDto agentDto = new AgentDto();
		agentDto.setAgentCode(agentModel.getAgentCode());
		agentDto.setAgentName(agentModel.getAgentName());
		agentDto.setLocation(agentModel.getLocation());
		return agentDto;
	}

	@Override
	public boolean availableAgent(String agentCode) throws Exception {
		List<AgentModel> agentModel = agentDao.findAgentByCode(agentCode);
		if (agentModel!= null && agentModel.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public List<AgentDto> findAgentByLocations(String loccodes) throws Exception {
		loccodes=loccodes.replaceAll(",$", "");
		List<AgentModel> agentModels = agentDao.findAgentByLocations(loccodes);
		List<AgentDto> agentDtos = new ArrayList<>();
		for (AgentModel agentModel : agentModels) {
			AgentDto agentDto = getAgent(agentModel);
			agentDtos.add(agentDto);
		}
		return agentDtos;
	}

	
	

}
