package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.ReceiptDetailsModel;
import org.springframework.jdbc.core.RowMapper;

public class ReceiptDetailsRowMapper implements RowMapper<ReceiptDetailsModel>{

	@Override
	public ReceiptDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReceiptDetailsModel model = new ReceiptDetailsModel();
		
		model.setCredat(rs.getDate("txndat"));
		model.setDoccod(rs.getString("doccod"));
		model.setDocnum(rs.getInt("docnum"));
		model.setPolnum(rs.getInt("polnum"));
		model.setPprnum(rs.getString("pprnum"));
		model.setTopprm(rs.getDouble("totprm"));
		model.setPaymod(rs.getString("paymod"));
		model.setChqrel(rs.getString("chqrel"));
		model.setName(rs.getString("ppdnam"));
		model.setUser(rs.getString("advcod"));
		model.setChqNo(rs.getString("chqnum"));
		
		return model;
	}

}
