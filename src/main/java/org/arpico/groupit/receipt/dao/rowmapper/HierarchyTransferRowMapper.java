package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.HierarchyTransferModel;
import org.springframework.jdbc.core.RowMapper;

public class HierarchyTransferRowMapper implements RowMapper<HierarchyTransferModel>{

	@Override
	public HierarchyTransferModel mapRow(ResultSet rs, int row) throws SQLException {
		HierarchyTransferModel hierarchyTransferModel=new HierarchyTransferModel();
		
		hierarchyTransferModel.setCls(rs.getString("agncls"));
		hierarchyTransferModel.setFrom(rs.getString("frmdat"));
		hierarchyTransferModel.setMasterSalesC(rs.getString("unlcod"));
		hierarchyTransferModel.setName(rs.getString("shrtnm"));
		hierarchyTransferModel.setTo(rs.getString("todate"));
		
		return hierarchyTransferModel;
	}

}
