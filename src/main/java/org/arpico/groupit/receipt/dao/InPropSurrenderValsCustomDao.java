package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;

public interface InPropSurrenderValsCustomDao {

	List<InPropSurrenderValsModel> getSurrenderValByInpprNoAndSeq(Integer pprNo, Integer seqNo) throws Exception;
}
