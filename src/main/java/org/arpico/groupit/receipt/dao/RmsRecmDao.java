package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.RmsRecmModel;
import org.arpico.groupit.receipt.model.pk.RmsRecmModelPK;
import org.springframework.data.repository.CrudRepository;

public interface RmsRecmDao extends CrudRepository<RmsRecmModel, RmsRecmModelPK>{

}
