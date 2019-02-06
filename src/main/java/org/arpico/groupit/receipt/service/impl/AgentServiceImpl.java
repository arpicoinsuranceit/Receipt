
package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.AgentService;
import org.arpico.groupit.receipt.util.DaoParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private JwtDecoder jwtDecoder;

	@Autowired
	private RmsUserDao rmsUserDao;

	@Autowired
	private DaoParameters daoParameters;
	
	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;

	@Override
	public List<AgentDto> getAgentList(Integer agentCode, String token) throws Exception {

		String sql = "";
		String userCode = jwtDecoder.generate(token);
		String userLoc = jwtDecoder.generateLoc(token);

		if (!userLoc.equalsIgnoreCase("HO")) {
			List<String> locations = rmsUserDao.getLocation(userCode);

			String locCodes = daoParameters.getParaForIn(locations);

			sql = "and loccod IN (" + locCodes + ")";

		}

		List<AgentModel> agentModels = agentDao.findAgentLikeAgentCode(agentCode, sql);
		List<AgentDto> agentDtos = new ArrayList<>();
		for (AgentModel agentModel : agentModels) {
			AgentDto agentDto = getAgent(agentModel);
			agentDtos.add(agentDto);
		}
		return agentDtos;
	}

	public AgentDto getAgent(AgentModel agentModel) {
		AgentDto agentDto = new AgentDto();
		agentDto.setAgentCode(agentModel.getAgentCode());
		agentDto.setAgentName(agentModel.getAgentName());
		agentDto.setLocation(agentModel.getLocation());
		return agentDto;
	}

	@Override
	public boolean availableAgent(String agentCode) throws Exception {
		List<AgentModel> agentModel = agentDao.findAgentByCode(agentCode);
		if (agentModel != null && agentModel.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<AgentDto> findAgentByLocations(String loccodes) throws Exception {
		loccodes = loccodes.replaceAll(",$", "");
		List<AgentModel> agentModels = agentDao.findAgentByLocations(loccodes);
		List<AgentDto> agentDtos = new ArrayList<>();
		for (AgentModel agentModel : agentModels) {
			AgentDto agentDto = getAgent(agentModel);
			agentDtos.add(agentDto);
		}
		return agentDtos;
	}

	@Override
	public AgentDto getAgentDetails(String agentCode) throws Exception {
		AgentModel agentModel = agentDao.findPropAgent(agentCode);
		AgentDto agentDto = new AgentDto();

		if (agentModel != null) {
			agentDto.setAgentCode(agentModel.getAgentCode());
			agentDto.setAgentName(agentModel.getAgentName());
			agentDto.setLocation(agentModel.getLocation());
		}

		return agentDto;
	}

	@Override
	public List<AgentDto> getAgentList(Integer agentCode, String token, String branchCode) throws Exception {
		String sql = "";
		sql = "and loccod IN ('" + branchCode + "')";

		List<AgentModel> agentModels = agentDao.findAgentLikeAgentCode(agentCode, sql);
		List<AgentDto> agentDtos = new ArrayList<>();
		for (AgentModel agentModel : agentModels) {
			AgentDto agentDto = getAgent(agentModel);
			agentDtos.add(agentDto);
		}
		return agentDtos;
	}
	
	@Override
	public List<AgentDto> getAgentListByRegion(Integer agentCode, String token, String branchCode) throws Exception {
		
		String regions="";
		String regionCodes[]=branchCode.split(",");
		
		if(regionCodes.length > 0) {
			for (String string : regionCodes) {
				regions+="'"+string+"'"+",";
			}
		}
		
		regions=regions.replaceAll(",$", "");
		
		List<String> brnCodes = branchUnderwriteDao.findLocCodesZonalBranch(regions,"REGION");
		
		String branches="";
		
		if(!brnCodes.isEmpty()) {
			for (String string : brnCodes) {
				branches+="'"+string+"'"+",";
			}
		}
		
		branches=branches.replaceAll(",$", "");
		
		String sql = "";
		sql = "and loccod IN (" + branches + ")";

		List<AgentModel> agentModels = agentDao.findAgentLikeAgentCode(agentCode, sql);
		List<AgentDto> agentDtos = new ArrayList<>();
		for (AgentModel agentModel : agentModels) {
			AgentDto agentDto = getAgent(agentModel);
			agentDtos.add(agentDto);
		}
		return agentDtos;
	}

}