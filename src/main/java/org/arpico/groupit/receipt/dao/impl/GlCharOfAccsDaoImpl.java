package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.GlCharOfAccsDao;
import org.arpico.groupit.receipt.dao.rowmapper.AccountGLRowMapper;
import org.arpico.groupit.receipt.model.AccountGLModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GlCharOfAccsDaoImpl implements GlCharOfAccsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AccountGLModel> getAccounts(String data) throws Exception {
		List<AccountGLModel> accountGLDtos = jdbcTemplate.query(
				"select INTERID, DESCRI from glcharofaccs where SBUCOD = '450' and INTERID in (" + data + ");",
				new AccountGLRowMapper());
		return accountGLDtos;
	}

}
