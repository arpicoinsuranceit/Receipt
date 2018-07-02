package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropMedicalReqModel;

public interface InPropMedicalReqCustomDao {

	List<InPropMedicalReqModel> getMedicalReqByPprNoAndSeq(Integer pprNo, Integer seqNo) throws Exception;
}
