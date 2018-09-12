package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.RmsDocTxnmGridModel;

public interface RmsDocTxnmCustomDao {

	List<RmsDocTxnmGridModel> findTop10(String creBy) throws Exception;

}
