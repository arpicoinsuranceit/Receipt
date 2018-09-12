package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.RmsItemMasterModel;

public interface RmsItemMasterCustomDao {
	
	RmsItemMasterModel findbyId(String itemCode) throws Exception;

}
