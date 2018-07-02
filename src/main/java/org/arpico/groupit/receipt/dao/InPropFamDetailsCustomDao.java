package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropFamDetailsModel;

public interface InPropFamDetailsCustomDao {

	List<InPropFamDetailsModel> getFamilyByPprNoAndSeqNo (Integer pprNo, Integer seqNo) throws Exception;
	
}
