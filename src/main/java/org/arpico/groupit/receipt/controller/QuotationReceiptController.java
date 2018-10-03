package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.QuotationReceiptService;
import org.arpico.groupit.receipt.validation.CommonValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class QuotationReceiptController {

	@Autowired
	private QuotationReceiptService quotationReceiptService;

	@Autowired
	private CommonValidations commonValidations;

	@RequestMapping(value = "/savereceiptquo", method = RequestMethod.POST)
	public ResponseEntity<Object> savereceiptquo(@RequestBody SaveReceiptDto saveReceiptDto) {
		System.out.println(saveReceiptDto.toString());

		System.out.println("Work");
		
		String valid = "Error";

		try {
			valid = commonValidations.validateQuotationReceiptInputs(saveReceiptDto);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(valid);
		try {
			if (valid.equalsIgnoreCase("ok")) {
				ResponseDto resp = quotationReceiptService.saveQuotationReceipt(saveReceiptDto);
				System.out.println(resp);
				if (resp.getCode().equalsIgnoreCase("200")) {
					
					return new ResponseEntity<>(resp, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(resp, HttpStatus.NOT_ACCEPTABLE);
				}

			} else {
				return new ResponseEntity<>(valid, HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@RequestMapping(value = "/getQuodetail", method = RequestMethod.POST)
	public ProposalBasicDetailsDto getPolicyBasicDetails(@RequestParam String polId, @RequestParam String prpseq) {
		
		try {
			return quotationReceiptService.getBasicDetails(Integer.parseInt(polId.trim()), Integer.parseInt(prpseq.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getBranches", method = RequestMethod.POST)
	public List<String> getBranches(@RequestParam String userCode) {
		
		try {
			return quotationReceiptService.getBranches(userCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
