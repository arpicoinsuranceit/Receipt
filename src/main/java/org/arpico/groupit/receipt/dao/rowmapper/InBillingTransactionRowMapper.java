package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.springframework.jdbc.core.RowMapper;

public class InBillingTransactionRowMapper implements RowMapper<InBillingTransactionsModel>{

	@Override
	public InBillingTransactionsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InBillingTransactionsModel model = new InBillingTransactionsModel();
		return model;
	}

}
