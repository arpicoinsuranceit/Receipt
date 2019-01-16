package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.RmsDocTxnmGridDto;
import org.arpico.groupit.receipt.service.MiscellaneousReceiptService;
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
public class MiscellaneousReceiptInvController {

	@Autowired
	private MiscellaneousReceiptService miscellaneousReceiptService;
	
	@Autowired
	private CommonValidations commonValidations;
	
	@RequestMapping(value = "/misinvreceiptsave/{token:.+}", method = RequestMethod.POST)
	public ResponseEntity<Object> saveReceipt (@RequestBody MiscellaneousReceiptInvDto dto, @PathVariable String token) throws Exception{
		
		//System.out.println(dto);
		//System.out.println(token);
		
		String validity = commonValidations.validateMiscellaneousReceiptInvInputs(dto, token);
		
		if(validity.equalsIgnoreCase("ok")){
			return miscellaneousReceiptService.save(dto, token);
		}else {
			ResponseDto responseDto = new ResponseDto();
			responseDto.setCode("204");
			responseDto.setStatus("Error");
			responseDto.setMessage(validity);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		}
		
		
	}
	
	
	
	@RequestMapping(value = "/emsdocmlast", method = RequestMethod.POST)
	public List<RmsDocTxnmGridDto> getLastReceipts(@RequestParam String token) throws Exception{
		
		return miscellaneousReceiptService.getLast(token);
	}
	
}
