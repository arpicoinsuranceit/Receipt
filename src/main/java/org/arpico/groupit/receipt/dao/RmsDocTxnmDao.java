package org.arpico.groupit.receipt.dao;


import org.arpico.groupit.receipt.model.RmsDocTxnmModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxnmModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RmsDocTxnmDao extends JpaRepository<RmsDocTxnmModel, RmsDocTxnmModelPK>{

	
}
