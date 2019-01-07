package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.BankModel;
import org.springframework.jdbc.core.RowMapper;

public class BankRowMapper implements RowMapper<BankModel>{

	@Override
	public BankModel mapRow(ResultSet rs, int row) throws SQLException {
		BankModel bankModel = new BankModel();
		
		bankModel.setBankCode(rs.getString("BANCOD"));
		bankModel.setBankName(rs.getString("BANDES"));
		bankModel.setLocation(rs.getString("loccod"));
		bankModel.setAcccode(rs.getString("accnam"));
		
		return bankModel;
	}

}
