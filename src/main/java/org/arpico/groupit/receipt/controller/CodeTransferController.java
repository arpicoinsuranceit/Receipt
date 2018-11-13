package org.arpico.groupit.receipt.controller;


import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class CodeTransferController {
	
	@Autowired
	private CodeTransferService codeTransferService;
	
	@RequestMapping(value="/code_transfer/getProposalDetails/{pprNum}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProposalDetails(@PathVariable("pprNum")String pprNum)throws Exception{
		return codeTransferService.getProposalDetails(pprNum);
	}
	
	@RequestMapping(value="/code_transfer/getPolicyDetails/{polNum}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPolicyDetails(@PathVariable("polNum")String polNum)throws Exception{
		return codeTransferService.getPolicyDetails(polNum);
	}
	
	@RequestMapping(value="/code_transfer/getAgentCode", method = RequestMethod.GET)
	public List<AgentDto> getAgentCodes()throws Exception{
		return codeTransferService.getAllAgents();
	}

}
