package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.arpico.groupit.receipt.model.AccountDetailsModel;
import org.springframework.jdbc.core.RowMapper;

public class AccountDetailsRowMapper implements RowMapper<AccountDetailsModel>{

	@Override
	public AccountDetailsModel mapRow(ResultSet rs, int row) throws SQLException {
		AccountDetailsModel accountModel = new AccountDetailsModel();
		
		accountModel.setAccNO(rs.getString("alacid"));
		accountModel.setBranch(rs.getString("dimm04"));
		accountModel.setDescription(rs.getString("descri"));
		accountModel.setCr(rs.getDouble("cramnt"));
		accountModel.setDr(rs.getDouble("dramnt"));
		
		return accountModel;
	}

}
