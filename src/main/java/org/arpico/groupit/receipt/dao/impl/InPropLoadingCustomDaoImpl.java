package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropLoadingCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropLoadingRowMapper;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropLoadingCustomDaoImpl implements InPropLoadingCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropLoadingModel> getPropLoadingBuPprNumAndSeq(Integer pprNo, Integer seqNo) throws Exception {
		/*
		 * return jdbcTemplate.query(
		 * "select * from inproploading where sbucod = '450' and pprnum = " + pprNo +
		 * " and prpseq =" + seqNo, new InPropLoadingRowMapper());
		 */

		return jdbcTemplate.query(
				"select * from inproploading where sbucod = '450' and pprnum = '" + pprNo + "' and prpseq =" + seqNo,
				new InPropLoadingRowMapper());

	}

	@Override
	public void removePropLoadingByPprNumAndSeq(Integer pprNo, Integer seqNo) throws Exception {

		jdbcTemplate.execute(
				"delete from inproploading where sbucod = '450' and pprnum = " + pprNo + " and prpseq =" + seqNo);
	}

}
