package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.ReFundModel;

public interface InBillingTransactionsCustomDao{

	List<InBillingTransactionsModel> getUnSetOffs(String pprnum) throws Exception;
	
	InBillingTransactionsModel getTxnYearDate (String pprnum) throws Exception;
	
	List<ReFundModel> getRefundList (String pprNum) throws Exception;
	
	Double paybleAmountThisMonth(Integer pprNo) throws Exception;

}
