package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;

public interface InTransactionCustomDao {
	
	List<LastReceiptSummeryModel>  getLastReceipts (String user) throws Exception;

}
