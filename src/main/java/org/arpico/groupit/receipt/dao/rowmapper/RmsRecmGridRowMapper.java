package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.RmsRecmGridModel;
import org.springframework.jdbc.core.RowMapper;

public class RmsRecmGridRowMapper implements RowMapper<RmsRecmGridModel>{

	@Override
	public RmsRecmGridModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RmsRecmGridModel model = new RmsRecmGridModel();
		
		model.setAmtfcu(rs.getDouble("AMTFCU"));
		model.setCreateDate(rs.getString("CRE_DATE"));
		model.setDocCode(rs.getString("DOC_CODE"));
		model.setDonNo(rs.getString("DOC_NO"));
		model.setRemark(rs.getString("REMARK"));
		
		return model;
	}

}
