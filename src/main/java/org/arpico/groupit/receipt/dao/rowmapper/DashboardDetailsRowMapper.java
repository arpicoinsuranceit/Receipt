package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.DashboardDetailsModel;
import org.springframework.jdbc.core.RowMapper;

public class DashboardDetailsRowMapper implements RowMapper<DashboardDetailsModel>{

	@Override
	public DashboardDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardDetailsModel model = new DashboardDetailsModel();
		
		model.setDocCode(rs.getString("DOCCODE"));
		model.setDocNumber(rs.getInt("DOCNUM"));
		model.setRemark(rs.getString("REMARK"));
		model.setAmount(rs.getDouble("AMOUNT"));
		model.setCreateDate(rs.getDate("CREATEDT"));
		return model;
	}

}
