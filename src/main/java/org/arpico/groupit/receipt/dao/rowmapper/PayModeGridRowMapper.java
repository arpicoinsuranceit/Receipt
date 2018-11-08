package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.PayModeGridModel;
import org.springframework.jdbc.core.RowMapper;

public class PayModeGridRowMapper implements RowMapper<PayModeGridModel>{

	@Override
	public PayModeGridModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayModeGridModel model = new PayModeGridModel();
		
		model.setAmount(rs.getDouble("amount"));
		model.setDay(rs.getString("day"));
		model.setMonth(rs.getString("month"));
		model.setYear(rs.getString("year"));
		model.setPayMode(rs.getString("paymod"));
		
		return model;
	}

}
