package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqCustomDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.dto.CodeTransferHelperDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> getProposalDetails(String pprNum) throws Exception {
		ResponseDto dto = null;
		try {
			InProposalsModel inProposalsModel=inProposalCustomDao.getProposalFromPprnum(Integer.valueOf(pprNum));
			if(inProposalsModel.getPprsta().equals("L3")) {
				try {
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
						
						return new ResponseEntity<>(codeTransferHelperDto, HttpStatus.OK);
						
					}else {
						dto = new ResponseDto();
						dto.setCode("204");
						dto.setStatus("Error");
						dto.setMessage("Unable to transfer code in this Proposal.");
						return new ResponseEntity<>(dto, HttpStatus.OK);
					}
				}catch(Exception ex) {
					dto = new ResponseDto();
					dto.setCode("204");
					dto.setStatus("Error");
					dto.setMessage("Unable to transfer code in this Proposal.");
					return new ResponseEntity<>(dto, HttpStatus.OK);
				}
				
				
			}else {
				dto = new ResponseDto();
				dto.setCode("204");
				dto.setStatus("Error");
				dto.setMessage("Unable to transfer code in this Proposal.");
				return new ResponseEntity<>(dto, HttpStatus.OK);
			
			}
		}catch(Exception e) {
			dto = new ResponseDto();
			dto.setCode("204");
			dto.setStatus("Error");
			dto.setMessage("Proposal Not Found");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<Object> getPolicyDetails(String polNum) throws Exception {
		ResponseDto dto = null;
		try {
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
				
				return new ResponseEntity<>(codeTransferHelperDto, HttpStatus.OK);
			}else {
				dto = new ResponseDto();
				dto.setCode("204");
				dto.setStatus("Error");
				dto.setMessage("Unable to transfer code in this Proposal.");
				return new ResponseEntity<>(dto, HttpStatus.OK);
			
			}
			
		}catch(Exception e) {
			dto = new ResponseDto();
			dto.setCode("204");
			dto.setStatus("Error");
			dto.setMessage("Policy No Not Found");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
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
