package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.RmsItemMasterModel;
import org.springframework.jdbc.core.RowMapper;

public class RmsItemmasterRowMapper implements RowMapper<RmsItemMasterModel>{

	@Override
	public RmsItemMasterModel mapRow(ResultSet rst, int arg1) throws SQLException {
		
		RmsItemMasterModel itemMasterModel = new RmsItemMasterModel();
		itemMasterModel.setItemCode(rst.getString("ITEM_CODE"));
		itemMasterModel.setUnit(rst.getString("UNIT"));
		itemMasterModel.setAvgPrice(rst.getDouble("AVG_PRICE"));
		itemMasterModel.setGlGroup(rst.getString("glgrup"));
		itemMasterModel.setItmGroup(rst.getString("ITM_GROUP"));
		itemMasterModel.setPluCode(rst.getString("PLU_CODE"));
		itemMasterModel.setUnitPrice(rst.getDouble("UNIT_PRICE"));
		
		return itemMasterModel;
	}

}
