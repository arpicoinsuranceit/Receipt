package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.dto.ReceiptDetailsDto;
import org.arpico.groupit.receipt.service.ReceiptInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ReceiptInquiryController {

	@Autowired
	private ReceiptInquiryService receiptInquiryService;
	
	
	@RequestMapping(value = "/loadReceiptInquiryDetails/{token}/{pageNum}/{limit}", method = RequestMethod.GET)
	public List<ReceiptDetailsDto> getAllReceiptDetails (@PathVariable("token") String token,@PathVariable("pageNum") Integer pageNum,@PathVariable("limit") Integer limit){
		System.out.println("loadReceiptInquiryDetails");
		
		try {
			return receiptInquiryService.getAllReceiptDetails(token, pageNum,limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
