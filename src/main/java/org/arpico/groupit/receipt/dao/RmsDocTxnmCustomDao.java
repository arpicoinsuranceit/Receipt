package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.RmsDocTxnmGridModel;
import org.arpico.groupit.receipt.model.RmsDocTxnmModel;

public interface RmsDocTxnmCustomDao {

	List<RmsDocTxnmGridModel> findTop10(String creBy) throws Exception;
	
	RmsDocTxnmModel getModel(String docCode, Integer docNum) throws Exception;

}
