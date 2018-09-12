package org.arpico.groupit.receipt.dao;


import org.arpico.groupit.receipt.model.RmsDocTxnmModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxnmModelPK;
import org.springframework.data.repository.CrudRepository;

public interface RmsDocTxnmDao extends CrudRepository<RmsDocTxnmModel, RmsDocTxnmModelPK>{

	
}
