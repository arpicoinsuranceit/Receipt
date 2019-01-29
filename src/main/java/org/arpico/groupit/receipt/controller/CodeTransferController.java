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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/code_transfer/getCodePendingProposalDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> getCodePendingProposalDetails(@RequestParam("token")String token)throws Exception{
		return codeTransferService.getCodePendingProposalDetails(token);
	}
	
	@RequestMapping(value="/code_transfer/getPolicyDetails/{polNum}/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPolicyDetails(@PathVariable("polNum")String polNum,@PathVariable("token")String token)throws Exception{
		return codeTransferService.getPolicyDetails(polNum,token);
	}
	
	@RequestMapping(value="/code_transfer/getPendingCodeTransfersPrp", method = RequestMethod.POST)
	public List<CodeTransferDto> getPendingCodeTransfersPrp(@RequestParam("token") String token)throws Exception{
		return codeTransferService.getPendingCodeTransferPrp(token);
	}
	
	@RequestMapping(value="/code_transfer/getCanceledCodeTransfersPol", method = RequestMethod.POST)
	public List<CodeTransferDto> getCanceledCodeTransfersPol(@RequestParam("token") String token)throws Exception{
		return codeTransferService.getCanceledCodeTransferPol(token);
	}
	
	
	@RequestMapping(value="/code_transfer/getPendingCodeTransfersPol", method = RequestMethod.POST)
	public List<CodeTransferDto> getPendingCodeTransfersPol(@RequestParam("token") String token)throws Exception{
		return codeTransferService.getPendingCodeTransferPol(token);
	}
	
	@RequestMapping(value="/code_transfer/getCanceledCodeTransfersPrp", method = RequestMethod.POST)
	public List<CodeTransferDto> getCanceledCodeTransfersPrp(@RequestParam("token") String token)throws Exception{
		return codeTransferService.getCanceledCodeTransferPrp(token);
	}
	
	
	@RequestMapping(value="/code_transfer/saveCodeTranPol", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCodeTranPol(@RequestBody SaveCodeTransferDto saveCodeTransferDto)throws Exception{
		
		//System.out.println(saveCodeTransferDto.toString());
		return codeTransferService.saveCodeTransferPol(saveCodeTransferDto);
	}
	
	@RequestMapping(value="/code_transfer/saveCodeTranPrp", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCodeTranPrp(@RequestBody SaveCodeTransferDto saveCodeTransferDto)throws Exception{
		
		//System.out.println(saveCodeTransferDto.toString());
		return codeTransferService.saveCodeTransferPrp(saveCodeTransferDto);
	}
	
	@RequestMapping(value="/code_transfer/rejectCodeTran", method = RequestMethod.POST)
	public ResponseEntity<Object> rejectCodeTran(@RequestParam("user")String user,@RequestParam("codeTransferId")String codeTransferId
			,@RequestParam("remark")String remark)throws Exception{
		
		return codeTransferService.rejectCodeTransfer(user,Integer.valueOf(codeTransferId),remark);
	}
	
	@RequestMapping(value="/code_transfer/approveCodeTran", method = RequestMethod.POST)
	public ResponseEntity<Object> approveCodeTran(@RequestParam("user")String user,@RequestParam("codeTransferId")String codeTransferId
			,@RequestParam("remark")String remark)throws Exception{
		
		return codeTransferService.approveCodeTransfer(user,Integer.valueOf(codeTransferId),remark);
	}
	
	@RequestMapping(value="/code_transfer/getCodeTransfersToApprove", method = RequestMethod.POST)
	public List<CodeTransferDto> getCodeTransfersToApprove(@RequestParam("userCode")String userCode)throws Exception{
		return codeTransferService.getCodeTransfersToApprove(userCode);
	}

}
