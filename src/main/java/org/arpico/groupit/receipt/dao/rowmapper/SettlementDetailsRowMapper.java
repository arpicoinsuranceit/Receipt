package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.SettlementDetailsModel;
import org.springframework.jdbc.core.RowMapper;

public class SettlementDetailsRowMapper implements RowMapper<SettlementDetailsModel>{

	@Override
	public SettlementDetailsModel mapRow(ResultSet r, int row) throws SQLException {
		SettlementDetailsModel settlementDetailsModel=new SettlementDetailsModel();
		
		settlementDetailsModel.setAccount(r.getString("accnum"));
		settlementDetailsModel.setType(r.getString("acctyp"));
		settlementDetailsModel.setBank(r.getString("bandes"));
		settlementDetailsModel.setBranch(r.getString("branam"));
		settlementDetailsModel.setFromDate(r.getDate("frmdat"));
		settlementDetailsModel.setToDate(r.getDate("todate"));
		
		return settlementDetailsModel;
	}

}
