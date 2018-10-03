package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.RmsRecdModel;
import org.arpico.groupit.receipt.model.pk.RmsRecdModelPK;
import org.springframework.data.repository.CrudRepository;

public interface RmsRecdDao extends CrudRepository<RmsRecdModel, RmsRecdModelPK>{

}
