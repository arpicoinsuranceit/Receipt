package org.arpico.groupit.receipt.controller;


import java.util.List;

import org.arpico.groupit.receipt.dto.CodeTransferDto;
import org.arpico.groupit.receipt.dto.SaveCodeTransferDto;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class CodeTransferController {
	
	@Autowired
	private CodeTransferService codeTransferService;
	
	@RequestMapping(value="/code_transfer/getProposalDetails/{pprNum}/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProposalDetails(@PathVariable("pprNum")String pprNum,@PathVariable("token")String token)throws Exception{
		return codeTransferService.getProposalDetails(pprNum,token);
	}
	
	@RequestMapping(value="/code_transfer/getPolicyDetails/{polNum}/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPolicyDetails(@PathVariable("polNum")String polNum,@PathVariable("token")String token)throws Exception{
		return codeTransferService.getPolicyDetails(polNum,token);
	}
	
	@RequestMapping(value="/code_transfer/getPendingCodeTransfersPrp/{token:.+}", method = RequestMethod.GET)
	public List<CodeTransferDto> getPendingCodeTransfersPrp(@PathVariable("token")String token)throws Exception{
		return codeTransferService.getPendingCodeTransferPrp(token);
	}
	
	@RequestMapping(value="/code_transfer/getPendingCodeTransfersPol/{token:.+}", method = RequestMethod.GET)
	public List<CodeTransferDto> getCanceledCodeTransfersPol(@PathVariable("token")String token)throws Exception{
		return codeTransferService.getCanceledCodeTransferPol(token);
	}
	
	
	@RequestMapping(value="/code_transfer/getCanceledCodeTransfersPol/{token:.+}", method = RequestMethod.GET)
	public List<CodeTransferDto> getPendingCodeTransfersPol(@PathVariable("token")String token)throws Exception{
		return codeTransferService.getPendingCodeTransferPol(token);
	}
	
	@RequestMapping(value="/code_transfer/getCanceledCodeTransfersPrp/{token:.+}", method = RequestMethod.GET)
	public List<CodeTransferDto> getCanceledCodeTransfersPrp(@PathVariable("token")String token)throws Exception{
		return codeTransferService.getCanceledCodeTransferPrp(token);
	}
	
	
	@RequestMapping(value="/code_transfer/saveCodeTranPol", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCodeTranPol(@RequestBody SaveCodeTransferDto saveCodeTransferDto)throws Exception{
		
		System.out.println(saveCodeTransferDto.toString());
		return codeTransferService.saveCodeTransferPol(saveCodeTransferDto);
	}
	
	@RequestMapping(value="/code_transfer/saveCodeTranPrp", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCodeTranPrp(@RequestBody SaveCodeTransferDto saveCodeTransferDto)throws Exception{
		
		System.out.println(saveCodeTransferDto.toString());
		return codeTransferService.saveCodeTransferPrp(saveCodeTransferDto);
	}
	
	@RequestMapping(value="/code_transfer/rejectCodeTran/{user}/{codeTransferId}/{remark}", method = RequestMethod.GET)
	public ResponseEntity<Object> rejectCodeTran(@PathVariable("user")String user,@PathVariable("codeTransferId")Integer codeTransferId
			,@PathVariable("remark")String remark)throws Exception{
		
		return codeTransferService.rejectCodeTransfer(user,codeTransferId,remark);
	}
	
	@RequestMapping(value="/code_transfer/approveCodeTran/{user}/{codeTransferId}/{remark}", method = RequestMethod.GET)
	public ResponseEntity<Object> approveCodeTran(@PathVariable("user")String user,@PathVariable("codeTransferId")Integer codeTransferId
			,@PathVariable("remark")String remark)throws Exception{
		
		return codeTransferService.approveCodeTransfer(user,codeTransferId,remark);
	}
	
	@RequestMapping(value="/code_transfer/getCodeTransfersToApprove/{token:.+}", method = RequestMethod.GET)
	public List<CodeTransferDto> getCodeTransfersToApprove(@PathVariable("token")String token)throws Exception{
		return codeTransferService.getCodeTransfersToApprove(token);
	}

}
