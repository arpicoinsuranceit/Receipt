package org.arpico.groupit.receipt.service.impl;

import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PolicySaveService {
	
	@Autowired
	private InTransactionsDao inTransactionDao;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionDao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.NESTED, readOnly = false, rollbackFor = Exception.class)
	public void saveReceipt(InTransactionsModel inTransactionsModel, InBillingTransactionsModel deposit) {
		inTransactionDao.save(inTransactionsModel);
		deposit.getBillingTransactionsModelPK().setDoccod("RCPLL");
		inBillingTransactionDao.save(deposit);
	}

	
}
