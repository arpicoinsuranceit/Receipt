package org.arpico.groupit.receipt.controller;

import org.arpico.groupit.receipt.service.SetoffRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SetoffController {

	@Autowired
	private SetoffRestService setoffRestService;

	@PostMapping(value = "/setoff")
	public String setoff(@RequestBody Integer policyNo) {
		try {
			return setoffRestService.setoffRest(policyNo);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "Error";
		}
		
	}
	
}
