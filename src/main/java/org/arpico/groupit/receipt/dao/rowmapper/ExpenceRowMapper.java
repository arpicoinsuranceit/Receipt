package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.ExpenseModel;
import org.springframework.jdbc.core.RowMapper;

public class ExpenceRowMapper implements RowMapper<ExpenseModel>{

	@Override
	public ExpenseModel mapRow(ResultSet rst, int arg1) throws SQLException {
		ExpenseModel expenseModel = new ExpenseModel();
		expenseModel.setExpenceId(rst.getString("ITEM_CODE"));
		expenseModel.setDescription(rst.getString("ITM_DESC"));
		expenseModel.setAmount(rst.getDouble("UNIT_PRICE"));
		return expenseModel;
	}

}
