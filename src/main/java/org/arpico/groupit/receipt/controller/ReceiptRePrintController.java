package org.arpico.groupit.receipt.controller;

import org.arpico.groupit.receipt.service.ReprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ReceiptRePrintController {
	
	@Autowired
	private ReprintService reprintService;
	
	@RequestMapping(value="receiptRePrint/{docCode}/{receiptNo}/{token:.+}")
	public ResponseEntity<Object> receiptRePrint(@PathVariable("docCode")String docCode,@PathVariable("receiptNo")Integer receiptNo, @PathVariable("token")String token) throws Exception {
		
		System.out.println(docCode +" docCode" + receiptNo +" receiptNo");
		
		return reprintService.getReprint(docCode, receiptNo, token);
	}

}
