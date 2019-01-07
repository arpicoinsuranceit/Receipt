package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.RmsRecdModel;
import org.arpico.groupit.receipt.model.pk.RmsRecdModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RmsRecdDao extends JpaRepository<RmsRecdModel, RmsRecdModelPK>{

}
