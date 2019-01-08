package org.arpico.groupit.receipt.dao.impl;

import org.arpico.groupit.receipt.dao.InLoanTransactionCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InLoanTransactionRowMapper;
import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InLoanTransactionCustomDaoImpl implements InLoanTransactionCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public InLoanTransactionsModel getLoanTransaction(String type, Integer no) throws Exception {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM inloantransactions where sbucod='450' and doccod = '" + type + "' and docnum = '" + no + "'",
				new InLoanTransactionRowMapper());
	}


}
