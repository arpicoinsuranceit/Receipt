package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InOcuLoadDetModel;

public interface InOccuLoadDatDao {

	public List<InOcuLoadDetModel> inOccuLoadDatDaosByOccupation( String ocuCode, String ridCode) throws Exception;
	
}
