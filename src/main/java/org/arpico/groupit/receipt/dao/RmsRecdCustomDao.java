package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.MisGlItemModel;

public interface RmsRecdCustomDao {
	
	public List<MisGlItemModel> glItemModels (String docCode, Integer docNo) throws Exception;

	public String getPayMode (String docCode, Integer docNo) throws Exception;
	
}
