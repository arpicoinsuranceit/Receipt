package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropSchedulesModel;

public interface InPropShedulesCustomDao {

	List<InPropSchedulesModel> getScheduleBuPprNoAndSeqNo(Integer pprNo, Integer seqNo) throws Exception;
}
