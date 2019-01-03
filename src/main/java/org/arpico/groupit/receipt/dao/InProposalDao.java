package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InProposalDao extends JpaRepository<InProposalsModel, InProposalsModelPK>{

}
