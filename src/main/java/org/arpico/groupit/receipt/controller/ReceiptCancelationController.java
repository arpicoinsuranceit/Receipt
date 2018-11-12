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
	
	@RequestMapping(value = "/saveRequest/{token}/{receiptNo}/{reason}/{doccod}", method = RequestMethod.GET)
	public ResponseEntity<Object> saveRequest(@PathVariable("receiptNo") String receiptNo,@PathVariable("reason") String reason,@PathVariable("token") String token
			,@PathVariable("doccod")String doccod){
		System.out.println("called...");
		try {
			System.out.println("called...");
			return receiptCancelationService.saveRequest(receiptNo, reason,token,doccod);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	@RequestMapping(value = "/getPendingRequest/{token:.+}", method = RequestMethod.GET)
	public List<CanceledReceiptDto> getPendingRequest (@PathVariable("token") String token) throws Exception{
		
		return receiptCancelationService.findPendingRequest(token);
		
	}
	
	@RequestMapping(value = "/getCanceledRequest/{token:.+}", method = RequestMethod.GET)
	public List<CanceledReceiptDto> getCanceledRequest (@PathVariable("token") String token) throws Exception{
		
		return receiptCancelationService.findCanceledRequest(token);
		
	}
	
}
