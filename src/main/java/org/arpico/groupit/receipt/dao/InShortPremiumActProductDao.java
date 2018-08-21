package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.InShortPremiumModel;
import org.arpico.groupit.receipt.model.pk.InShortPremiumModelPK;
import org.springframework.data.repository.CrudRepository;

public interface InShortPremiumActProductDao extends CrudRepository<InShortPremiumModel, InShortPremiumModelPK>{
	
	InShortPremiumModel findByStatusAndInShortPremiumModelPK(String status, InShortPremiumModelPK inShortPremiumModelPK) throws Exception;

}
