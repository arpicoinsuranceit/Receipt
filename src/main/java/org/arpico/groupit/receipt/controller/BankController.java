package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.dto.BankDto;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@RequestMapping(value = "/getbank", method = RequestMethod.POST)
	public List<BankDto> getBankDtos(@RequestParam String token){
		String userCode = new JwtDecoder().generate(token);
		System.out.println(userCode);
		try {
			return bankService.getBanksByUser(userCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
