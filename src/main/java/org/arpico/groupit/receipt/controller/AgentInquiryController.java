package org.arpico.groupit.receipt.controller;

import java.util.List;

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

	@GetMapping("/getlist/{token:.+}/{page}/{offset}")
	public List<AgnInqAgnListDto> getAgentList(@PathVariable String token, @PathVariable Integer page, @PathVariable Integer offset) throws Exception {

		List<AgnInqAgnListDto> agnListDtos = agentInquiryService.getAgentListByBranch(token, page, offset);

		return agnListDtos;

	}
	
	@GetMapping("/getlistCount/{token:.+}")
	public Integer getAgentListCount(@PathVariable String token, @PathVariable Integer page, @PathVariable Integer offset) throws Exception {

		Integer count = agentInquiryService.getAgentListByBranchCountLength(token);

		return count;

	}

}
