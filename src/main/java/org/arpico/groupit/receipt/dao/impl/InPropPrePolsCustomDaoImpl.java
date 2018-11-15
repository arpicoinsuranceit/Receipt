package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropPrePolsCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropPrePolsRowMapper;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropPrePolsCustomDaoImpl implements InPropPrePolsCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropPrePolsModel> getPrePolByPprNoAndPprSeq(Integer pprNo, Integer pprSeq) throws Exception {

		return jdbcTemplate.query(
				"select * from inpropprepols where sbucod = '450' and pprnum = " + pprNo + " and prpseq =" + pprSeq,
				new InPropPrePolsRowMapper());
	}
	
	@Override
	public void removePrePolByPprNoAndPprSeq(Integer pprNo, Integer pprSeq) throws Exception {

		jdbcTemplate.execute(
				"delete from inpropprepols where sbucod = '450' and pprnum = " + pprNo + " and prpseq =" + pprSeq );
	}

}
