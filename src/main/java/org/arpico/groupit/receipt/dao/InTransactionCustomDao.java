package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;
import org.arpico.groupit.receipt.model.NotRelChequeModel;

public interface InTransactionCustomDao {

	List<LastReceiptSummeryModel> getLastReceipts(String user) throws Exception;

	List<LastReceiptSummeryModel> getLastReceiptsByPprNo(String pprNo) throws Exception;

	List<LastReceiptSummeryModel> getLastReceiptsByPolNo(String polNo)throws Exception;

	List<LastReceiptSummeryModel> getReceiptsByDocNum(String docnum)throws Exception;
	
	InTransactionsModel getTransaction (String type, Integer no) throws Exception;
	
	List<InTransactionsModel> getTransactionByPprNum (String pprNum) throws Exception;

	List<LastReceiptSummeryModel> getLastReceiptsByProposal(String pprnum) throws Exception;

	List<LastReceiptSummeryModel> getLastLoanReceiptsByPolNo(String polNo) throws Exception;

	List<NotRelChequeModel> getNotRelCheques(String sql) throws Exception;

	Integer updatePolNum(String pprnum, String polnum) throws Exception;
	
	
}
