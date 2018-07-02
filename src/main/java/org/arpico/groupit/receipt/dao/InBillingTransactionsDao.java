package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.springframework.data.repository.CrudRepository;

public interface InBillingTransactionsDao extends CrudRepository<InBillingTransactionsModel, InBillingTransactionsModelPK>{

}
