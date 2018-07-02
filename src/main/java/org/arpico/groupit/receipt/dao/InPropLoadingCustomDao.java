package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropLoadingModel;

public interface InPropLoadingCustomDao {
	
	List<InPropLoadingModel> getPropLoadingBuPprNumAndSeq(Integer pprNo, Integer seqNo) throws Exception;
}
