package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.pk.InPropMedicalReqModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InPropMedicalReqDao extends JpaRepository<InPropMedicalReqModel, InPropMedicalReqModelPK>{

}
