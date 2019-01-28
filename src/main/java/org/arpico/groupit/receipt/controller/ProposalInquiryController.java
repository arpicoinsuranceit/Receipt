package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.DataTableResponseDto;
import org.arpico.groupit.receipt.dto.ProposalInquiryDataDto;
import org.arpico.groupit.receipt.service.ProposalInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ProposalInquiryController {
	
	@Autowired
	private ProposalInquiryService proposalInquiryService;
	
	@GetMapping(value = { "/getPropoInqCount/{token:.+}/{equality}/{column}/{data}", "/getPropoInqCount/{token:.+}/{equality}/{column}"})
	public Integer getCount (@PathVariable String token, @PathVariable String equality, @PathVariable String column, @PathVariable(required = false) String data) {
		
		System.out.println(equality + " " +  column + " " + data);
		
		return proposalInquiryService.getCount(token ,equality, column, data);
		
	}
	
	@GetMapping(value = { "/propoInqloadData/{token:.+}/{page}/{offset}/{equality}/{column}/{data}", "/propoInqloadData/{token:.+}/{page}/{offset}/{equality}/{column}"})
	public DataTableResponseDto getCount (@PathVariable String token, @PathVariable Integer page, @PathVariable Integer offset, @PathVariable String equality, @PathVariable String column, @PathVariable(required = false) String data) {
		
		System.out.println(equality + " " +  column + " " + data);
		
		return proposalInquiryService.propoInqloadData(token, page, offset, equality, column, data);
		
	}
	
	@GetMapping(value = "/propoInqloadData/{token:.+}/{proposalNo}")
	public ProposalInquiryDataDto getInfo (@PathVariable String token, @PathVariable String proposalNo ) throws Exception {
		
		System.out.println(proposalNo);
		
		return proposalInquiryService.loadInfo(token, proposalNo);
		
	}
	
	

}
