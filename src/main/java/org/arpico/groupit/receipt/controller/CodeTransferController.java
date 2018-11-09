package org.arpico.groupit.receipt.controller;


import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.dto.CodeTransferHelperDto;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeTransferController {
	
	@Autowired
	private CodeTransferService codeTransferService;
	
	@RequestMapping(value="/getProposalDetails", method = RequestMethod.GET)
	public CodeTransferHelperDto getProposalDetails(@PathVariable("pprNum")String pprNum)throws Exception{
		return codeTransferService.getProposalDetails(pprNum);
	}
	
	@RequestMapping(value="/getPolicyDetails", method = RequestMethod.GET)
	public CodeTransferHelperDto getPolicyDetails(@PathVariable("polNum")String polNum)throws Exception{
		return codeTransferService.getPolicyDetails(polNum);
	}
	
	@RequestMapping(value="/getAgentCode", method = RequestMethod.GET)
	public List<AgentDto> getAgentCodes()throws Exception{
		return codeTransferService.getAllAgents();
	}

}
