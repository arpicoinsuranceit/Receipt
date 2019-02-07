package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.PaymentHistoryModel;
import org.arpico.groupit.receipt.model.ReFundModel;

public interface InBillingTransactionsCustomDao{

	List<InBillingTransactionsModel> getUnSetOffs(String pprnum) throws Exception;
	
	InBillingTransactionsModel getTxnYearDate (String pprnum) throws Exception;
	
	//List<ReFundModel> getRefundList (String pprNum) throws Exception;
	
	List<InBillingTransactionsModel> getRefundList (String pprNum) throws Exception;
	
	Double paybleAmountThisMonth(Integer pprNo) throws Exception;

	InBillingTransactionsModel getLasiInvoice(String pprnum) throws Exception;
	
	List<InBillingTransactionsModel> getTransactionsByPprnum(String pprnum) throws Exception;
	
	List<PaymentHistoryModel> getPaymentHistory (String pprNum) throws Exception;
	
	List<InBillingTransactionsModel> getSetoffsForRcpl(Integer docnum, String docCode) throws Exception;

	Integer updatePolNum(String pprnum, String polnum) throws Exception;

	InBillingTransactionsModel getLastDeposit(String pprnum) throws Exception;
}
