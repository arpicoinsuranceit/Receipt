package org.arpico.groupit.receipt.dao;

import java.util.List;

public interface LoanReceiptDao {
	
	List<Integer> findLoanNoByPolnum(String polnum)throws Exception;
}
