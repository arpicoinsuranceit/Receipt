package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InLoanTransactionsModelPK;
import org.springframework.data.repository.CrudRepository;

public interface InLoanTransactionsDao extends CrudRepository<InLoanTransactionsModel, InLoanTransactionsModelPK> {

}
