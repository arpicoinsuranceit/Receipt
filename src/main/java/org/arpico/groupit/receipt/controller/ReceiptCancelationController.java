package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.dto.CanceledReceiptDto;
import org.arpico.groupit.receipt.service.ReceiptCancelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public List<String> getReceipts (@PathVariable("token") String token,@PathVariable("receiptId") String receiptId) throws Exception{
		
		return receiptCancelationService.findReceiptLikeReceiptId(receiptId, token);
	}
	
	@RequestMapping(value = "/saveRequest/{token}/{receiptNo}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<Object> saveRequest(@PathVariable("receiptNo") String receiptNo,@PathVariable("reason") String reason,@PathVariable("token") String token){
		
		try {
			return receiptCancelationService.saveRequest(receiptNo, reason,token);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/getPendingRequest/{token:.+}", method = RequestMethod.GET)
	public List<CanceledReceiptDto> getPendingRequest (@PathVariable("token") String token) throws Exception{
		
		return receiptCancelationService.findPendingRequest(token);
		
	}
	
}
