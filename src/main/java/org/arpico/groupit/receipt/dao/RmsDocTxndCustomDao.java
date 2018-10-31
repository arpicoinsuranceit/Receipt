package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.RmsDocTxndModel;

public interface RmsDocTxndCustomDao {
	
	List<RmsDocTxndModel> getDocTxndModels (String docCode, Integer docNum) throws Exception;

}
