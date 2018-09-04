package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.dto.AccountDetailsDto;
import org.arpico.groupit.receipt.dto.BankDetailsDto;
import org.arpico.groupit.receipt.dto.LoadReceiptInquiryDetailsDto;
import org.arpico.groupit.receipt.dto.PolicyDetailsDto;
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
	public LoadReceiptInquiryDetailsDto getAllReceiptDetails (@PathVariable("token") String token,@PathVariable("pageNum") Integer pageNum,@PathVariable("limit") Integer limit){
		System.out.println("loadReceiptInquiryDetails");
		
		try {
			return receiptInquiryService.getAllReceiptDetails(token, pageNum,limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/loadBankDetails/{docCode}/{docNum}", method = RequestMethod.GET)
	public BankDetailsDto getAllBankDetails (@PathVariable("docCode") String docCode,@PathVariable("docNum") Integer docNum){
		System.out.println("getAllBankDetails");
		
		try {
			return receiptInquiryService.getBankDetails(docCode, docNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/loadPolicyDetails/{docCode}/{docNum}", method = RequestMethod.GET)
	public List<PolicyDetailsDto> getAllPolicyDetails (@PathVariable("docCode") String docCode,@PathVariable("docNum") Integer docNum){
		System.out.println("getAllPolicyDetails");
		
		try {
			return receiptInquiryService.getAllPolicyDetails(docCode, docNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/loadAccountDetails/{docCode}/{docNum}", method = RequestMethod.GET)
	public List<AccountDetailsDto> getAllAccountDetails (@PathVariable("docCode") String docCode,@PathVariable("docNum") Integer docNum){
		System.out.println("getAllAccountDetails");
		
		try {
			return receiptInquiryService.getAllAccountDetails(docCode, docNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
