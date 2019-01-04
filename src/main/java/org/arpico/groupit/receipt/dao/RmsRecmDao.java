package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.RmsRecmModel;
import org.arpico.groupit.receipt.model.pk.RmsRecmModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RmsRecmDao extends JpaRepository<RmsRecmModel, RmsRecmModelPK>{

}
