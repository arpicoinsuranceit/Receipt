package org.arpico.groupit.receipt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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

	@GetMapping("/getlist/{token:.+}")
	public List<AgnInqAgnListDto> getAgentList(@PathVariable String token) throws Exception {

		List<AgnInqAgnListDto> agnListDtos = agentInquiryService.getAgentListByBranch(token);

		
		
		return agnListDtos;

	}

}
