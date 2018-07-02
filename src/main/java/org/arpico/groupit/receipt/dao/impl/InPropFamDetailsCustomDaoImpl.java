package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropFamDetailsCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropFamDetailsRowMapper;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropFamDetailsCustomDaoImpl implements InPropFamDetailsCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropFamDetailsModel> getFamilyByPprNoAndSeqNo(Integer pprNo, Integer seqNo) throws Exception {

		return jdbcTemplate.query(
				"select * from inpropfamdetails where sbucod = '450' and pprnum = " + pprNo + " and prpseq = " + seqNo,
				new InPropFamDetailsRowMapper());
	}

}
