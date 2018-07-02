package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;
import org.springframework.jdbc.core.RowMapper;

public class LastReceiptRowMapper implements RowMapper<LastReceiptSummeryModel>{

	@Override
	public LastReceiptSummeryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		LastReceiptSummeryModel model = new LastReceiptSummeryModel();
		
		model.setCreadt(rs.getDate("creadt"));
		model.setDoccod(rs.getString("doccod"));
		model.setDocnum(rs.getInt("docnum"));
		model.setPolnum(rs.getInt("polnum"));
		model.setPprnum(rs.getString("pprnum"));
		model.setTotprm(rs.getDouble("totprm"));
		
		return model;
	}

}
