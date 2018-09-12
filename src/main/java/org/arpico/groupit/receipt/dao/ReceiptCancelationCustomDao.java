package org.arpico.groupit.receipt.dao;

import java.util.List;

public interface ReceiptCancelationCustomDao {

	List<String> findReceiptLikeReceiptId(String receiptId,String loccodes) throws Exception;
	
}
