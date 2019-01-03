package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.pk.InPropFamDetailsModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InPropFamDetailsDao extends JpaRepository<InPropFamDetailsModel, InPropFamDetailsModelPK>{

}
