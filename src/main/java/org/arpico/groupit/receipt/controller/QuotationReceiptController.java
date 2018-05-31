package org.arpico.groupit.receipt.controller;

import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.QuotationReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class QuotationReceiptController {
	
	@Autowired
	private QuotationReceiptService quotationReceiptService;
	
	@RequestMapping(value = "/savereceiptquo" , method = RequestMethod.POST)
	public ResponseEntity<Object> savereceiptquo(@RequestBody SaveReceiptDto saveReceiptDto){
		System.out.println(saveReceiptDto.toString());
		try {
			quotationReceiptService.saveQuotationReceipt(saveReceiptDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
