package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.service.ReceiptCancelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ReceiptCancelationController {

	@Autowired
	private ReceiptCancelationService receiptCancelationService;
	
	@RequestMapping(value = "/getReceipts/{token}/{receiptId}", method = RequestMethod.GET)
	public List<String> getReceipts (@PathVariable("token") String token,@PathVariable("receiptId") String receiptId){
		
		try {
			return receiptCancelationService.findReceiptLikeReceiptId(receiptId, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/saveRequest/{token}/{receiptNo}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<Object> saveRequest(@PathVariable("receiptNo") String receiptNo,@PathVariable("reason") String reason,@PathVariable("token") String token){
		
		try {
			return receiptCancelationService.saveRequest(receiptNo, reason,token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
