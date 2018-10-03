package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.RmsRecmGridModel;

public interface RmsRecmCustomDao {
	
	List<RmsRecmGridModel> findTop10(String creBy) throws Exception;

}
