package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.GlTranTempModel;
import org.arpico.groupit.receipt.model.pk.GlTranTempModelPK;
import org.springframework.data.repository.CrudRepository;

public interface GlTranTempDao extends CrudRepository<GlTranTempModel, GlTranTempModelPK>{

}
