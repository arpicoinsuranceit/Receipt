package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		
		try {
			return agentService.getAgentList(agentCode, token);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return null;
	}
	
}
