package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqCustomDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.dto.CodeTransferHelperDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeTransferServiceImpl implements CodeTransferService{
	
	@Autowired
	private InProposalCustomDao inProposalCustomDao;
	
	@Autowired
	private InPropMedicalReqCustomDao inPropMedicalReqCustomDao; 
	
	@Autowired
	private AgentDao agentDao;

	@Override
	public CodeTransferHelperDto getProposalDetails(String pprNum) throws Exception {
		InProposalsModel inProposalsModel=inProposalCustomDao.getProposalFromPprnum(Integer.valueOf(pprNum));
		if(inProposalsModel.getPprsta().equals("L3")) {
			InPropMedicalReqModel inPropMedicalReqModels=inPropMedicalReqCustomDao.getMedicalReq(Integer.valueOf(pprNum), inProposalsModel.getInProposalsModelPK().getPrpseq(),"AD-CT","N");
			if(inPropMedicalReqModels != null) {
				CodeTransferHelperDto codeTransferHelperDto=new CodeTransferHelperDto();
				codeTransferHelperDto.setAgentCode(inProposalsModel.getAdvcod());
				codeTransferHelperDto.setPprNum(pprNum);
				codeTransferHelperDto.setBranch(inProposalsModel.getInProposalsModelPK().getLoccod());
				AgentModel agentModel=agentDao.findPropAgent(inProposalsModel.getAdvcod());
				if(agentModel != null) {
					codeTransferHelperDto.setAgentName(agentModel.getAgentName());
					codeTransferHelperDto.setDesignation(agentModel.getDesignation());
				}
				
				return codeTransferHelperDto;
				
			}else {
				return null;
			}
			
		}else {
			return null;
		}
		
	}

	@Override
	public CodeTransferHelperDto getPolicyDetails(String polNum) throws Exception {
		InProposalsModel inProposalsModel=inProposalCustomDao.getProposalFromPolnum(Integer.valueOf(polNum));
		
		if(inProposalsModel != null) {
			CodeTransferHelperDto codeTransferHelperDto=new CodeTransferHelperDto();
			codeTransferHelperDto.setAgentCode(inProposalsModel.getAdvcod());
			codeTransferHelperDto.setPprNum(polNum);
			codeTransferHelperDto.setBranch(inProposalsModel.getInProposalsModelPK().getLoccod());
			AgentModel agentModel=agentDao.findPropAgent(inProposalsModel.getAdvcod());
			if(agentModel != null) {
				codeTransferHelperDto.setAgentName(agentModel.getAgentName());
				codeTransferHelperDto.setDesignation(agentModel.getDesignation());
			}
			
			return codeTransferHelperDto;
		}
		
		return null;
	}

	@Override
	public List<AgentDto> getAllAgents() throws Exception {
		List<AgentModel> agentModels=agentDao.getAllAgents();
		List<AgentDto> agentDtos=new ArrayList<>();
		
		if(agentModels != null) {
			agentModels.forEach(ag -> {
				AgentDto agentDto=new AgentDto();
				agentDto.setAgentCode(ag.getAgentCode());
				agentDto.setAgentName(ag.getAgentName());
				agentDto.setLocation(ag.getLocation());
				
				agentDtos.add(agentDto);
				
			});
		}
		
		
		return agentDtos;
	}
	

}
