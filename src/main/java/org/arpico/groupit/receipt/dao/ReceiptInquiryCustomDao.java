package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.AccountDetailsModel;
import org.arpico.groupit.receipt.model.BankDetailsModel;
import org.arpico.groupit.receipt.model.PolicyDetailsModel;
import org.arpico.groupit.receipt.model.ReceiptDetailsModel;

public interface ReceiptInquiryCustomDao{
	
	public List<ReceiptDetailsModel> getAllReceiptDetails(Integer offset,String loccodes,boolean isHO,Integer limit)throws Exception;
	
	public Integer getAllReceiptCount(String loccodes,boolean isHO)throws Exception;
	
	public List<PolicyDetailsModel> getAllPolicyDetails(String docCode,Integer docNum)throws Exception;

	public List<AccountDetailsModel> getAllAccountDetails(String docCode,Integer docNum)throws Exception;

	public BankDetailsModel getBankDetails(String docCode,Integer docNum)throws Exception;

	
}
