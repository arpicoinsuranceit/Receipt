package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.ReFundModel;
import org.springframework.jdbc.core.RowMapper;

public class ReFundAmntRowMapper implements RowMapper<ReFundModel>{

	@Override
	public ReFundModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReFundModel fundModel = new ReFundModel();
		
		fundModel.setDoccod(rs.getString("doccod"));
		fundModel.setDocnum(rs.getInt("docnum"));
		fundModel.setPprnum(rs.getInt("pprnum"));
		fundModel.setRefamount(rs.getDouble("refamount"));
		
		return fundModel;
	}

}
