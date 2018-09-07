package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.ExpenseDto;
import org.arpico.groupit.receipt.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*")
public class MiscellaniousReceiptController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping(value = "/getexpences", method = RequestMethod.POST)
	public List<ExpenseDto> getExpenseDto (@RequestParam String token) throws Exception {
		
		return expenseService.getExpense();
		
	}

}
