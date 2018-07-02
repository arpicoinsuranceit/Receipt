package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropShedulesCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPropScheduleRowMapper;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InPropShedulesCustomDaoImpl implements InPropShedulesCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropSchedulesModel> getScheduleBuPprNoAndSeqNo(Integer pprNo, Integer seqNo) throws Exception {

		return jdbcTemplate.query(
				"select * from inpropschedules where sbucod = '450' and pprnum = " + pprNo + " and prpseq =" + seqNo,
				new InPropScheduleRowMapper());
	}

}
