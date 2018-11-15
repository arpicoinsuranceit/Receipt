package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropAddBenefictCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropAddBenefitRowMapper;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropAddBenefictCustomDaoImpl implements InPropAddBenefictCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropAddBenefitModel> getBenefByPprSeq(Integer pprNo, Integer seqNo) throws Exception {

		return jdbcTemplate.query("select * from inpropaddbenefit where sbucod = '450' and pprnum = " + pprNo + " and prpseq = " + seqNo,
				new InPropAddBenefitRowMapper());
	}
	
	@Override
	public void removeBenefByPprSeq(Integer pprNo, Integer seqNo) throws Exception {

		jdbcTemplate.execute("delete from inpropaddbenefit where sbucod = '450' and  pprnum = " + pprNo + " and prpseq = " + seqNo );
	}

}
