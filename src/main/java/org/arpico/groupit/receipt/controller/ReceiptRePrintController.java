package org.arpico.groupit.receipt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ReceiptRePrintController {
	
	@RequestMapping(value="receiptRePrint/{docCode}/{receiptNo}")
	public byte[] receiptRePrint(@PathVariable("docCode")String docCode,@PathVariable("receiptNo")Integer receiptNo) {
		
		System.out.println(docCode +" docCode" + receiptNo +" receiptNo");
		
		return null;
	}

}
