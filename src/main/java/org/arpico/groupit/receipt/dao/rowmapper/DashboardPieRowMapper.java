package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.DashboardPieModel;
import org.springframework.jdbc.core.RowMapper;

public class DashboardPieRowMapper implements RowMapper<DashboardPieModel>{

	@Override
	public DashboardPieModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardPieModel model = new DashboardPieModel();
		
		model.setAmount(rs.getDouble("amount"));
		model.setCount(rs.getInt("count"));
		model.setDocCode(rs.getString("doccod"));
		
		return model;
	}

}
