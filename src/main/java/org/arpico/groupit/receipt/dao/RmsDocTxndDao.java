package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxndModelPK;
import org.springframework.data.repository.CrudRepository;

public interface RmsDocTxndDao extends CrudRepository<RmsDocTxndModel, RmsDocTxndModelPK>{

}
