package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InTransactionsModelPK;
import org.springframework.data.repository.CrudRepository;

public interface InTransactionsDao extends CrudRepository<InTransactionsModel, InTransactionsModelPK>{

}
