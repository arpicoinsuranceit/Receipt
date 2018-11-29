package org.arpico.groupit.receipt.dao.impl;

import org.arpico.groupit.receipt.dao.RmsGlAccCodesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RmsGlAccCodesDaoImpl implements RmsGlAccCodesDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String getAccCode(String docCode) throws Exception {
		String accCode = null;

		accCode = jdbcTemplate.queryForObject(
				"select acc_code from rms_gl_acc_codes where sbu_code = '450' and doc_code = '" + docCode + "'", String.class);

		return accCode;
	}

}
