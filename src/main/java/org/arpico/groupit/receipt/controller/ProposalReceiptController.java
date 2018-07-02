package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.ProposalServce;
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
@CrossOrigin (origins = "*")
public class ProposalReceiptController {

	@Autowired
	private ProposalServce proposalServce;

	@RequestMapping(value = "/getProposal/{val}", method = RequestMethod.GET)
	public List<ProposalNoSeqNoDto> getProposalNSeqNo(@PathVariable String val) {
		System.out.println(val);
		try {
			return proposalServce.getProposalNoSeqNoDtoList(val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getproposaldetail", method = RequestMethod.POST)
	public ProposalBasicDetailsDto getProposalBasicDetails(@RequestParam String propId, @RequestParam String prpseq) {
		
		try {
			return proposalServce.getBasicDetails(Integer.parseInt(propId.trim()), Integer.parseInt(prpseq.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/savereceiptProp", method = RequestMethod.POST)
	public ResponseEntity<Object> saveProposalReceipt(@RequestBody SaveReceiptDto saveReceiptDto) {
		System.out.println(saveReceiptDto.toString());
		 
		try {
			return proposalServce.saveProposal(saveReceiptDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
