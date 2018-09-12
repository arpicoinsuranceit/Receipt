package org.arpico.groupit.receipt.controller;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.RmsDocTxnmGridDto;
import org.arpico.groupit.receipt.service.MiscellaneousReceiptService;
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
public class MiscellaneousReceiptInvController {

	@Autowired
	private MiscellaneousReceiptService miscellaneousReceiptService;
	
	@RequestMapping(value = "/misinvreceiptsave/{token:.+}", method = RequestMethod.POST)
	public ResponseEntity<Object> saveReceipt (@RequestBody MiscellaneousReceiptInvDto dto, @PathVariable String token) throws Exception{
		
		System.out.println(dto);
		System.out.println(token);
		
		return miscellaneousReceiptService.save(dto, token);
	}
	
	
	
	@RequestMapping(value = "/emsdocmlast", method = RequestMethod.POST)
	public List<RmsDocTxnmGridDto> getLastReceipts(@RequestParam String token) throws Exception{
		
		return miscellaneousReceiptService.getLast(token);
	}
	
}
