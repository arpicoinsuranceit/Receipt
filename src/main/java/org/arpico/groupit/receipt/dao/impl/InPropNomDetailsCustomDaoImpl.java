package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropNomDetailsCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropNomDetailsRowMapper;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropNomDetailsCustomDaoImpl implements InPropNomDetailsCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropNomDetailsModel> getNomByPprNoAndPprSeq(Integer pprNo, Integer pprSeq) throws Exception {

		return jdbcTemplate.query(
				"select * from inpropnomdetails where sbucod = '450' and pprnum = " + pprNo + " and prpseq =" + pprSeq,
				new InPropNomDetailsRowMapper());
	}

}
