package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AccountDetailsDto;
import org.arpico.groupit.receipt.dto.BankDetailsDto;
import org.arpico.groupit.receipt.dto.LoadReceiptInquiryDetailsDto;
import org.arpico.groupit.receipt.dto.PolicyDetailsDto;

public interface ReceiptInquiryService {
	
	public LoadReceiptInquiryDetailsDto getAllReceiptDetails(String token,Integer pageNum,Integer limit)throws Exception;
	
	public List<PolicyDetailsDto> getAllPolicyDetails(String docCode,Integer docNum)throws Exception;

	public List<AccountDetailsDto> getAllAccountDetails(String docCode,Integer docNum)throws Exception;

	public BankDetailsDto getBankDetails(String docCode,Integer docNum)throws Exception;
}
