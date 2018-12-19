package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.LoanReceiptService;
import org.arpico.groupit.receipt.service.PolicyReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoanReceiptController {

	@Autowired
	private PolicyReceiptService policyReceiptService;
	
	@Autowired
	private LoanReceiptService loanReceiptService;
	
	@RequestMapping(value = "/loan_receipt/policysearch/{val}", method = RequestMethod.GET)
	public List<ProposalNoSeqNoDto> getProposalNSeqNo(@PathVariable String val) {
		System.out.println(val);
		try {
			return policyReceiptService.getPolicyNoSeqNoDtoListLoanRcpt(val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loan_receipt/getpolicydetail", method = RequestMethod.POST)
	public ProposalBasicDetailsDto getPolicyBasicDetails(@RequestParam String polId, @RequestParam String prpseq) {
		
		try {
			return loanReceiptService.getBasicDetails(Integer.parseInt(polId.trim()), Integer.parseInt(prpseq.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loan_receipt/savereceiptLoan", method = RequestMethod.POST)
	public ResponseEntity<Object> savePolicyReceipt(@RequestBody SaveReceiptDto saveReceiptDto) {
		//System.out.println(saveReceiptDto.toString());
		 
		try {
			return loanReceiptService.saveLoanReceipt(saveReceiptDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loan_receipt/getloannumbers", method = RequestMethod.POST)
	public List<Integer> getLoanNumbers(@RequestBody String polnum) {
		
		try {
			return loanReceiptService.findLoanNoByPolnum(polnum.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
