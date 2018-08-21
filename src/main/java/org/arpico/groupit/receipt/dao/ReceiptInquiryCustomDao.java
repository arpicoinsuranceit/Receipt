package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.ReceiptDetailsModel;

public interface ReceiptInquiryCustomDao{
	
	public List<ReceiptDetailsModel> getAllReceiptDetails(Integer offset,String loccodes,boolean isHO,Integer limit)throws Exception;
	
}
