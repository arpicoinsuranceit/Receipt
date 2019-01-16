package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.ProposalServce;
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
@CrossOrigin (origins = "*")
public class ProposalReceiptController {

	@Autowired
	private ProposalServce proposalServce;

	@Autowired
	private CommonValidations commonValidations;
	
	@RequestMapping(value = "/getProposal/{val}", method = RequestMethod.GET)
	public List<ProposalNoSeqNoDto> getProposalNSeqNo(@PathVariable String val) {
		//System.out.println(val);
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
		//System.out.println(saveReceiptDto.toString());
		
		System.out.println("Proposal Save Controller");
		System.out.println("Proposal Save Controller Data : " + saveReceiptDto.toString());
		 
		try {
			String validity = commonValidations.validateProposalReceiptInputs(saveReceiptDto);
			
			if(validity.equalsIgnoreCase("ok")) {
				
				System.out.println("Proposal Save Controller Validation PASS");
				
				return proposalServce.saveProposal(saveReceiptDto);
			} else {
				
				System.out.println("Proposal Save Controller Validation FAIL : " + validity);
				ResponseDto responseDto = new ResponseDto();
				responseDto.setCode("204");
				responseDto.setStatus("Error");
				responseDto.setMessage(validity);
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			
			System.out.println("Proposal Save Controller Validation Error");
			
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
