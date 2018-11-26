package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropNomDetailsModel;

public interface InPropNomDetailsCustomDao {

	List<InPropNomDetailsModel> getNomByPprNoAndPprSeq(Integer pprNo, Integer pprSeq) throws Exception;

	void removeNomByPprNoAndPprSeq(Integer pprNo, Integer pprSeq) throws Exception;
}
