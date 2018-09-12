package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.RmsDocTxnmGridModel;
import org.springframework.jdbc.core.RowMapper;

public class RmsDocTxnmGridRowMapper implements RowMapper<RmsDocTxnmGridModel>{

	@Override
	public RmsDocTxnmGridModel mapRow(ResultSet rst, int arg1) throws SQLException {
		RmsDocTxnmGridModel model = new RmsDocTxnmGridModel();
		model.setAgentCode(rst.getString("REF1"));
		model.setDate(rst.getDate("CRE_DATE"));
		model.setDocCode(rst.getString("DOC_CODE"));
		model.setAmount(rst.getDouble("amtfcu"));
		model.setDocNo(rst.getInt("DOC_NO"));
		return model;
	}

}
