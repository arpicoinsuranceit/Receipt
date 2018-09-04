package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.arpico.groupit.receipt.model.BankDetailsModel;
import org.springframework.jdbc.core.RowMapper;

public class BankDetailsRowMapper implements RowMapper<BankDetailsModel>{

	@Override
	public BankDetailsModel mapRow(ResultSet rs, int row) throws SQLException {
		BankDetailsModel bankModel = new BankDetailsModel();
		
		bankModel.setBranchCode(rs.getString("chqbnk"));
		bankModel.setColBank(rs.getString("bancod"));
		bankModel.setAmount(rs.getDouble("totprm"));
		bankModel.setInsDate(rs.getDate("txndat"));
		bankModel.setInsNo(rs.getString("chqnum"));
		bankModel.setInsType(rs.getString("paymod"));
		bankModel.setRemarks(rs.getString("remark"));
		bankModel.setStatus(rs.getString("rctsta"));
		
		return bankModel;
	}

}
