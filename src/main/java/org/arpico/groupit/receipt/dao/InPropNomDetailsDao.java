package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.pk.InPropNomDetailsModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InPropNomDetailsDao extends JpaRepository<InPropNomDetailsModel, InPropNomDetailsModelPK>{

}
