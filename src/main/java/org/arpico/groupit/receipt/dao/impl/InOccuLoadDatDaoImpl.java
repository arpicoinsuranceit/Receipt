package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InOccuLoadDatDao;
import org.arpico.groupit.receipt.dao.rowmapper.InOccuLoadDatRowMapper;
import org.arpico.groupit.receipt.model.InOcuLoadDetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InOccuLoadDatDaoImpl implements InOccuLoadDatDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InOcuLoadDetModel> inOccuLoadDatDaosByOccupation(String ocuCode, String ridCode) throws Exception {
		/*
		 * List<InOcuLoadDetModel> datDaos = jdbcTemplate
		 * .query("select * from inoculoaddet where sbucod = '450' and ocucod = '" +
		 * ocuCode + "' and ridcod = '" + ridCode + "'", new InOccuLoadDatRowMapper());
		 */

		List<InOcuLoadDetModel> datDaos = jdbcTemplate
				.query("select * from inoculoaddet where sbucod = '450' and ocucod = '" + ocuCode + "' and ridcod = '"
						+ ridCode + "'", new InOccuLoadDatRowMapper());
		return datDaos;
	}

}
