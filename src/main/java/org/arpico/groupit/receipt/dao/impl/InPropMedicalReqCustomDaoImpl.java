package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropMedicalReqCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropMedicalReqRowMapper;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropMedicalReqCustomDaoImpl implements InPropMedicalReqCustomDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropMedicalReqModel> getMedicalReqByPprNoAndSeq(Integer pprNo, Integer seqNo) throws Exception {

		/*
		 * return jdbcTemplate.query(
		 * "select * from inpropmedicalreq where sbucod = '450' and pprnum = " + pprNo +
		 * " and prpseq = " + seqNo, new InPropMedicalReqRowMapper());
		 */

		return jdbcTemplate.query("select * from inpropmedicalreq where sbucod = '450' and pprnum = '" + pprNo
				+ "' and prpseq = " + seqNo, new InPropMedicalReqRowMapper());
	}

	@Override
	public InPropMedicalReqModel getMedicalReq(Integer pprNo, Integer seqNo, String medcod, String testStatus)
			throws Exception {
//
//		return jdbcTemplate.queryForObject(
//				"select * from inpropmedicalreq where sbucod = '450' and pprnum = " + pprNo + " and prpseq = " + seqNo
//						+ " and medcod = '" + medcod + "' and tessta ='" + testStatus + "' ",
//				new InPropMedicalReqRowMapper());

		return jdbcTemplate.queryForObject(
				"select * from inpropmedicalreq where sbucod = '450' and pprnum = '" + pprNo + "' and prpseq = '"
						+ seqNo + "' and medcod = '" + medcod + "' and tessta ='" + testStatus + "'",
				new InPropMedicalReqRowMapper());
	}

	@Override
	public void removeMedicalReqByPprNoAndSeq(Integer pprNo, Integer seqNo) throws Exception {

		jdbcTemplate.execute(
				"delete from inpropmedicalreq where sbucod = '450' and pprnum = " + pprNo + " and prpseq = " + seqNo);
	}

}
