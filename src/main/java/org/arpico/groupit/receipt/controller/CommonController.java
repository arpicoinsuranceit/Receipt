package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.arpico.groupit.receipt.util.CurrencyFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "*")
public class CommonController {
	
	@Autowired
	private InTransactionService inTransactionService;
	
	@RequestMapping(value = "/convertNumberToWord/{number}")
	public String convertNumberToWord(@PathVariable Double number) {
		
		CurrencyFormat currencyFormat = new CurrencyFormat();
		
		return currencyFormat.numberToWords(number);
	}
	
	@RequestMapping(value = "/getLastReceipts", method = RequestMethod.POST)
	public List<LastReceiptSummeryDto> getLastReceipts(@RequestParam String token) {
		
		try {
			return inTransactionService.getLastReceipts(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
