package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.ExpenseDao;
import org.arpico.groupit.receipt.dao.rowmapper.ExpenceRowMapper;
import org.arpico.groupit.receipt.model.ExpenseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseDaoImpl implements ExpenseDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ExpenseModel> getExpenceModels() throws Exception {
		List<ExpenseModel> expenseModels = jdbcTemplate.query("select ITEM_CODE, ITM_DESC, UNIT_PRICE from rms_itmmaster where ITM_GROUP in ('COM')", new ExpenceRowMapper());
		
		return expenseModels;
	}
}
