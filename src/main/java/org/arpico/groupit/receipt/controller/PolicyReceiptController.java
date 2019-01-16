package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
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
public class PolicyReceiptController {

	@Autowired
	private PolicyReceiptService policyReceiptService;

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

		System.out.println("POLICY RECEIPT SAVE");

		try {
			return policyReceiptService.savePolicyReceipt(saveReceiptDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
