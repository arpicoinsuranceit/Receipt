package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;

public interface ReceiptCancelationCustomDao {

	List<String> findReceiptLikeReceiptId(String receiptId,String loccodes,boolean isHo) throws Exception;
	
	List<CanceledReceiptModel> findPendingRequest(String loccodes,String status,boolean isHo) throws Exception;
	
	String findGMEmail(String sbucode,String loccode) throws Exception;

	
	List<CanceledReceiptModel> findPendingRequest(String locations, String string, boolean contains, Integer page,
			Integer offset) throws Exception;

	Integer findPendingRequestLength(String locations, String string, boolean contains)  throws Exception;

	InTransactionsModel findTransctionRow(String sbucode, String docnum, String doccod, String creby, boolean b) throws Exception;
	
	InLoanTransactionsModel findLoanTransctionRow(String sbucode, String docnum, String doccod, String creby, boolean b) throws Exception;

	
	
}
