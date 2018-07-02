package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.BankModel;

public interface BankDao {
	
	List<BankModel> getBankList(String dataSql) throws Exception;
	
	BankModel getBankById(String bankId) throws Exception;
	
}
