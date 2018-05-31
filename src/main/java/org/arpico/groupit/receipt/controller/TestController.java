package org.arpico.groupit.receipt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TestController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "work";
	}
}
