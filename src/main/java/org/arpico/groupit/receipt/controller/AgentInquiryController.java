package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentMasterDto;
import org.arpico.groupit.receipt.dto.AgnInqAgnListDto;
import org.arpico.groupit.receipt.service.AgentInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("agentInquiry")
public class AgentInquiryController {
	
	@Autowired
	private AgentInquiryService agentInquiryService;

	@GetMapping(value = {"/getlist/{token:.+}/{page}/{offset}/{equality}/{column}/{data}" , "/getlist/{token:.+}/{page}/{offset}/{equality}/{column}"})
	public List<AgnInqAgnListDto> getAgentList(@PathVariable String token, @PathVariable Integer page, @PathVariable Integer offset,
			 @PathVariable String equality, @PathVariable String column, @PathVariable(required = false) String data) throws Exception {

		List<AgnInqAgnListDto> agnListDtos = agentInquiryService.getAgentListByBranch(token, page, offset,equality,column,data);

		return agnListDtos;

	}
	
	@GetMapping(value= {"/getlistCount/{token:.+}/{equality}/{column}/{data}" , "/getlistCount/{token:.+}/{equality}/{column}"})
	public Integer getAgentListCount(@PathVariable String token, @PathVariable String equality, @PathVariable String column, @PathVariable(required = false) String data) throws Exception {

		Integer count = agentInquiryService.getAgentListByBranchCountLength(token,equality,column,data);

		return count;

	}
	
	@GetMapping("/getAgentDetails/{agentCode:.+}")
	public AgentMasterDto getAgentDetails(@PathVariable String agentCode)throws Exception{
		return agentInquiryService.getAgentFullDetails(agentCode);
	}

}
