package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InLoanTransactionsModel;

public interface InLoanTransactionCustomDao {

	InLoanTransactionsModel getLoanTransaction (String type, Integer no) throws Exception;
	
}
