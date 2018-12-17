package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropMedicalReqModel;

public interface InPropMedicalReqCustomDao {

	List<InPropMedicalReqModel> getMedicalReqByPprNoAndSeq(Integer pprNo, Integer seqNo) throws Exception;

	InPropMedicalReqModel getMedicalReq(Integer pprNo, Integer seqNo, String medcod, String testStatus)
			throws Exception;

	void removeMedicalReqByPprNoAndSeq(Integer pprNo, Integer seqNo) throws Exception;

	List<InPropMedicalReqModel> getMedicalReqForCodeTransfer(String medcod, String testStatus) throws Exception;
}
