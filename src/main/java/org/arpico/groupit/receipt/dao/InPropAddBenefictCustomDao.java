package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropAddBenefitModel;

public interface InPropAddBenefictCustomDao {

	List<InPropAddBenefitModel> getBenefByPprSeq(Integer pprNo, Integer seqNo) throws Exception;

	void removeBenefByPprSeq(Integer pprNo, Integer seqNo) throws Exception;
	
}
