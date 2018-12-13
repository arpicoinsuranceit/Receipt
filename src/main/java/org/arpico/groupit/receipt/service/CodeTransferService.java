package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.CodeTransferDto;
import org.arpico.groupit.receipt.dto.SaveCodeTransferDto;
import org.springframework.http.ResponseEntity;

public interface CodeTransferService {
	
	public List<CodeTransferDto> getPendingCodeTransferPrp(String token)throws Exception;
	
	public List<CodeTransferDto> getPendingCodeTransferPol(String token)throws Exception;
	
	public List<CodeTransferDto> getCanceledCodeTransferPrp(String token)throws Exception;
	
	public List<CodeTransferDto> getCanceledCodeTransferPol(String token)throws Exception;
	
	public ResponseEntity<Object> saveCodeTransferPrp(SaveCodeTransferDto saveCodeTransferDto)throws Exception;
	
	public ResponseEntity<Object> saveCodeTransferPol(SaveCodeTransferDto saveCodeTransferDto)throws Exception;

	public ResponseEntity<Object> rejectCodeTransfer(String user, Integer codeTransferId, String remark)throws Exception;

	public ResponseEntity<Object> approveCodeTransfer(String user, Integer codeTransferId, String remark)throws Exception;

	ResponseEntity<Object> getProposalDetails(String pprNum, String token) throws Exception;

	ResponseEntity<Object> getPolicyDetails(String polNum, String token) throws Exception;

	public List<CodeTransferDto> getCodeTransfersToApprove(String token) throws Exception;

	public ResponseEntity<Object> getCodePendingProposalDetails(String token) throws Exception;
	

}
