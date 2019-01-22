package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.PolicyReceiptService;
import org.arpico.groupit.receipt.validation.CommonValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PolicyReceiptController {

	@Autowired
	private PolicyReceiptService policyReceiptService;
	
	@Autowired
	private CommonValidations commonValidations;

	@RequestMapping(value = "/policysearch/{val}", method = RequestMethod.GET)
	public List<ProposalNoSeqNoDto> getProposalNSeqNo(@PathVariable String val) {
		// System.out.println(val);
		try {
			return policyReceiptService.getPolicyNoSeqNoDtoList(val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getpolicydetail", method = RequestMethod.POST)
	public ProposalBasicDetailsDto getPolicyBasicDetails(@RequestParam String polId, @RequestParam String prpseq) {

		try {
			return policyReceiptService.getBasicDetails(Integer.parseInt(polId.trim()),
					Integer.parseInt(prpseq.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/savereceiptPol", method = RequestMethod.POST)
	public ResponseEntity<Object> savePolicyReceipt(@RequestBody SaveReceiptDto saveReceiptDto) {
		// System.out.println(saveReceiptDto.toString());
		
		String valid = "Error";

		try {
			valid = commonValidations.validatePolicyreceipt(saveReceiptDto);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		System.out.println("POLICY RECEIPT SAVE");

		
		
		try {
			
			if (valid.equalsIgnoreCase("ok")) {
				
				System.out.println("POLICY RECEIPT SAVE VALIDATION PASS");
				
				return policyReceiptService.savePolicyReceipt(saveReceiptDto);
			} else {
				
				System.out.println("POLICY RECEIPT SAVE VALIDATION FAIL");
				
				return new ResponseEntity<>(valid, HttpStatus.NOT_ACCEPTABLE);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
