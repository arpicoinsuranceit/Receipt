package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.DashboardCashFlowSummeryModel;
import org.springframework.jdbc.core.RowMapper;

public class DashboardCashFlowSummeryRowMapper implements RowMapper<DashboardCashFlowSummeryModel>{

	@Override
	public DashboardCashFlowSummeryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardCashFlowSummeryModel model = new DashboardCashFlowSummeryModel();
		
		model.setAmount(rs.getDouble("AMOUNT"));
		model.setCount(rs.getInt("COUNT"));
		model.setDocCode(rs.getString("DOCCODE"));
		model.setPayMode(rs.getString("PAYMODE"));
		
		return model;
	}

}
