package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPropPrePolsModel;

public interface InPropPrePolsCustomDao {

	List<InPropPrePolsModel> getPrePolByPprNoAndPprSeq(Integer pprNo, Integer pprSeq) throws Exception;
	
}
