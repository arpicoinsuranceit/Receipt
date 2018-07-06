package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;

public interface InTransactionCustomDao {

	List<LastReceiptSummeryModel> getLastReceipts(String user) throws Exception;

	List<LastReceiptSummeryModel> getLastReceiptsByPprNo(String pprNo) throws Exception;

	List<LastReceiptSummeryModel> getLastReceiptsByPolNo(String polNo)throws Exception;


}
