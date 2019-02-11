package org.arpico.groupit.receipt.controller;

import org.arpico.groupit.receipt.service.AutoIssueRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class AutoIssueController {

	@Autowired
	public AutoIssueRestService autoIssueRestService; 
	
	@PostMapping(value = "/autoissue")
	public String autoIssue(@RequestBody Integer pprno){
		try {
			return autoIssueRestService.autoIssue(pprno);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
}
