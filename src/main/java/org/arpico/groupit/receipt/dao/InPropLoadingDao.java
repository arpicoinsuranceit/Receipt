package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.pk.InPropLoadingModelPK;
import org.springframework.data.repository.CrudRepository;

public interface InPropLoadingDao extends CrudRepository<InPropLoadingModel, InPropLoadingModelPK> {

}
