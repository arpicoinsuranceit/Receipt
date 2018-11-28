package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;

	@RequestMapping(value = "/getAllPromises/{token:.+}/{page}/{offset}", method = RequestMethod.GET)
	public List<PromisesGridDto> getAllPromises(@PathVariable String token, @PathVariable Integer page,
			@PathVariable Integer offset) throws Exception {

		return workflowService.getPromisesList(token, page, offset);

	}
	
	@GetMapping(value = "/getPaginatorLength/{token:.+}")
	public Integer getPaginatorLength(@PathVariable String token) throws Exception{
		return workflowService.getLength (token);
	}
	
	@PostMapping(value = "/addpromise/{token:.+}")
	public ResponseEntity<Object> getPaginatorLength(@PathVariable String token, @RequestBody PromisesGridDto promise) throws Exception{
		
		System.out.println(token);
		System.out.println(promise);
		
		return workflowService.savePromise(promise, token);
		
	}
	
	@PostMapping(value = "/settlepromise/{token:.+}")
	public ResponseEntity<Object> settlePromise(@PathVariable String token, @RequestBody PromisesGridDto promise) throws Exception{
		
		return workflowService.settlePromise(promise, token);
		
	}

}
