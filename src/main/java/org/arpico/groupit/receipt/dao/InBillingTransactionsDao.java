package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InBillingTransactionsDao extends JpaRepository<InBillingTransactionsModel, InBillingTransactionsModelPK>{


}
