package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.arpico.groupit.receipt.client.UserManagementClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dto.AgnInqAgnListDto;
import org.arpico.groupit.receipt.model.AgnInqAgnListModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.AgentInquiryService;
import org.arpico.groupit.receipt.util.DaoParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentInquiryServiceImpl implements AgentInquiryService {
	
	@Autowired
	private UserManagementClient userManagementClient;
	
	@Autowired
	private AgentDao agentDao;
	
	@Autowired
	private DaoParameters daoParameters; 

	@Override
	public List<AgnInqAgnListDto> getAgentListByBranch(String token, Integer page, Integer offset) throws Exception {
		
		String agentCode = new JwtDecoder().generate(token);

		String agentBranch = userManagementClient.getBranch(agentCode);
		
		System.out.println(agentBranch);

		String[] tempArr = agentBranch.split(",");
		
		List<String> agentBranchs = new ArrayList<>(Arrays.asList(tempArr));
		
		String locCodes = daoParameters.getParaForIn(agentBranchs);
		
		List<AgnInqAgnListDto> agnInqAgnListDtos = new ArrayList<>();

		List<AgnInqAgnListModel> agnInqAgnListModels = agentDao.getAgnInqList(locCodes, page, offset);

		agnInqAgnListModels.forEach(e -> {
			agnInqAgnListDtos.add(getAgnInqListDto(e));
		});

		return agnInqAgnListDtos;
	}

	public AgnInqAgnListDto getAgnInqListDto(AgnInqAgnListModel e) {
		AgnInqAgnListDto dto = new AgnInqAgnListDto();

		dto.setAgncod(e.getAgncod());
		dto.setAgndob(e.getAgndob());
		dto.setAgnnam(e.getAgnnam());
		dto.setAgnnic(e.getAgnnic());
		dto.setAgnrdt(e.getAgnrdt());
		dto.setAgnsta(e.getAgnsta());
		dto.setSliirg(e.getSliirg());
		dto.setSubdcd(e.getSubdcd());
		dto.setSupvid(e.getSupvid());

		return dto;
	}

	@Override
	public Integer getAgentListByBranchCountLength(String token) throws Exception {
		
		
		String agentCode = new JwtDecoder().generate(token);

		String agentBranch = userManagementClient.getBranch(agentCode);
		
		System.out.println(agentBranch);

		String[] tempArr = agentBranch.split(",");
		
		List<String> agentBranchs = new ArrayList<>(Arrays.asList(tempArr));
		
		String locCodes = daoParameters.getParaForIn(agentBranchs);
		
		Integer count = agentDao.getAgnInqListCount(locCodes);
		
		return count;
	}

}