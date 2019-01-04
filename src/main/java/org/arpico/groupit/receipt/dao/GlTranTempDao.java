package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.GlTranTempModel;
import org.arpico.groupit.receipt.model.pk.GlTranTempModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlTranTempDao extends JpaRepository<GlTranTempModel, GlTranTempModelPK>{

}
