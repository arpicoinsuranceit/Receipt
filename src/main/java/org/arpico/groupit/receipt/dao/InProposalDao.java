package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
import org.springframework.data.repository.CrudRepository;

public interface InProposalDao extends CrudRepository<InProposalsModel, InProposalsModelPK>{

}
