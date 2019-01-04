package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InTransactionsModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InTransactionsDao extends JpaRepository<InTransactionsModel, InTransactionsModelPK> {

}
