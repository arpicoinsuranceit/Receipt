package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.LoanReceiptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class LoanReceiptDaoImpl implements LoanReceiptDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Integer> findLoanNoByPolnum(String polnum) throws Exception {
		List<Integer> loanNoList = null;
		
		loanNoList= jdbcTemplate.query(" SELECT fclnum FROM inpolicyloan where sbucod = '450' and polnum = '"
						+ polnum + "' order by fclnum desc", new ResultSetExtractor<List<Integer>>() {

			@Override
			public List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Integer> loanNoListTemp = new ArrayList<>();
				while(rs.next()) {
					Integer loanNo=rs.getInt("fclnum");
					loanNoListTemp.add(loanNo);
				}
				return loanNoListTemp;
			}
		});
		
		return loanNoList;
				
	}
	
	

}
