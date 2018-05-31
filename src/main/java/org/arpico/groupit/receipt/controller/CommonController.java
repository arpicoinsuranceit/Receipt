package org.arpico.groupit.receipt.controller;

import org.arpico.groupit.receipt.util.CurrencyFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class CommonController {
	
	
	
	@RequestMapping(value = "/convertNumberToWord/{number}")
	public String convertNumberToWord(@PathVariable Double number) {
		
		CurrencyFormat currencyFormat = new CurrencyFormat();
		
		return currencyFormat.numberToWords(number);
	}

}
