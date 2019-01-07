package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.pk.InPropSchedulesModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InPropShedulesDao extends JpaRepository<InPropSchedulesModel, InPropSchedulesModelPK>{

}
