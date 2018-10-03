package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.AccountGLModel;

public interface GlCharOfAccsDao {

	public List<AccountGLModel> getAccounts (String data) throws Exception;
	
}
