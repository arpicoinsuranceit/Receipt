package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.BranchModel;
import org.springframework.jdbc.core.RowMapper;

public class BranchRowMapper implements RowMapper<BranchModel> {

	@Override
	public BranchModel mapRow(ResultSet rst, int arg1) throws SQLException {
		BranchModel branchModel = new BranchModel();
		branchModel.setDescription(rst.getString("loc_name"));
		branchModel.setId(rst.getString("loc_code"));
		return branchModel;
	}

	

}
