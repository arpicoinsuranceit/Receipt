package org.arpico.groupit.receipt.dao.impl;

import org.arpico.groupit.receipt.dao.InGLIntegParametersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InGLIntegParametersDaoImpl implements InGLIntegParametersDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String getAccCode(String bankCod) throws Exception {

		String accCode = jdbcTemplate.queryForObject(
				"select acccod from inglintegparameters where sbucod='450' and oprcod='RCNB' and amtcom = '" + bankCod
						+ "' and crdrty='DR' and amtyp='net_deposit_add'",
				String.class);

		return accCode;
	}

}
