package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.CanceledReceiptModel;

public interface ReceiptCancelationCustomDao {

	List<String> findReceiptLikeReceiptId(String receiptId,String loccodes) throws Exception;
	
	List<CanceledReceiptModel> findPendingRequest(String loccodes,String status) throws Exception;
	
	
}
