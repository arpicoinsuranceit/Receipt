package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.pk.InPropLoadingModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InPropLoadingDao extends JpaRepository<InPropLoadingModel, InPropLoadingModelPK> {

}
