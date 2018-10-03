package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.AccountGLModel;
import org.springframework.jdbc.core.RowMapper;

public class AccountGLRowMapper implements RowMapper<AccountGLModel>{

	@Override
	public AccountGLModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		AccountGLModel model = new AccountGLModel();
		
		model.setId(rs.getInt("INTERID"));
		model.setDescription(rs.getString("DESCRI"));
		
		return model;
	}

}
