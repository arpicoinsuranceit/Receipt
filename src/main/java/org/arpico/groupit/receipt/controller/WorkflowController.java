package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;

	@GetMapping(value = "/getAllPromises/{token.:}")
	public List<PromisesGridDto> getAllPromises(@PathVariable String token) throws Exception {

		return workflowService.getPromisesList(token);

	}

}
