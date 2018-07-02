package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropAddBenefitModel;

public interface BenefictDetailsDao {
	
	List<InPropAddBenefitModel> getBenefictByProduct(String product) throws Exception;
	
}
