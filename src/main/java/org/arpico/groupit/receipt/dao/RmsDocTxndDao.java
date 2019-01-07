package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxndModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RmsDocTxndDao extends JpaRepository<RmsDocTxndModel, RmsDocTxndModelPK>{

}
