package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropSurrenderValsCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropSurrenderValsRowMapper;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropSurrenderValsCustomDaoImpl implements InPropSurrenderValsCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropSurrenderValsModel> getSurrenderValByInpprNoAndSeq(Integer pprNo, Integer seqNo)
			throws Exception {
		System.out.println(pprNo);
		System.out.println(seqNo);
		return jdbcTemplate.query("select * from inpropsurrendervals where sbucod = '450' and pprnum = " + pprNo + " and prpseq =" + seqNo , new InPropSurrenderValsRowMapper());
	}
	
	@Override
	public void removeSurrenderValByInpprNoAndSeq(Integer pprNo, Integer seqNo)
			throws Exception {

		jdbcTemplate.execute("delete from inpropsurrendervals where sbucod = '450' and pprnum = " + pprNo
				+ " and prpseq =" + seqNo );
	}

}
