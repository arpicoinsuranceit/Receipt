package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.DashboardGridModel;
import org.springframework.jdbc.core.RowMapper;

public class DashboardGridRowMapper implements RowMapper<DashboardGridModel>{

	@Override
	public DashboardGridModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardGridModel model = new DashboardGridModel();
		
		model.setAmount(rs.getDouble("amount"));
		model.setCount(rs.getInt("count"));
		model.setDocCode(rs.getString("doccod"));
		model.setDay(rs.getString("day"));
		model.setMonth(rs.getString("month"));
		model.setYear(rs.getString("year"));
		
		return model;
	}

}
