package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AgentController {

	@Autowired
	private AgentService agentService;
	
	@RequestMapping(value = "/getAgents", method = RequestMethod.POST)
	public List<AgentDto> getAgentDtos (@RequestParam Integer agentCode, @RequestParam String token){
		System.out.println(agentCode + " agentCode");
		try {
			return agentService.getAgentList(agentCode, token);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return null;
	}
	
	@RequestMapping(value = "/getAgentByBranch", method = RequestMethod.POST)
	public List<AgentDto> getAgentDtos (@RequestParam Integer agentCode, @RequestParam String token, @RequestParam String branchCode){
		
		try {
			return agentService.getAgentList(agentCode, token, branchCode);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return null;
	}
	
	@RequestMapping(value = "/getAgentsDetails", method = RequestMethod.POST)
	public AgentDto getAgentDetails (@RequestBody String agentCode){
		
		try {
			return agentService.getAgentDetails(agentCode);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return null;
	}
	
}
