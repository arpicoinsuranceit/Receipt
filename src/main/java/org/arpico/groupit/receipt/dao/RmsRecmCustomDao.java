package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.RmsRecmGridModel;
import org.arpico.groupit.receipt.model.RmsRecmModel;

public interface RmsRecmCustomDao {
	
	List<RmsRecmGridModel> findTop10(String creBy) throws Exception;
	
	RmsRecmModel getRecm(String docCode, Integer docNo) throws Exception;

}
