package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.RmsRecmCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.RmsRecmGridRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.RmsRecmRowMapper;
import org.arpico.groupit.receipt.model.RmsRecmGridModel;
import org.arpico.groupit.receipt.model.RmsRecmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RmsRecmCustomDaoImpl implements RmsRecmCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RmsRecmGridModel> findTop10(String creBy) throws Exception {
		String sql = "select CRE_DATE, DOC_CODE, DOC_NO, REMARK, AMTFCU  from rms_recm WHERE SBU_CODE='450' and CRE_BY = '" + creBy
				+ "' ORDER BY CRE_DATE DESC, DOC_NO DESC  LIMIT 10";
		List<RmsRecmGridModel> gridModels = jdbcTemplate.query(sql, new RmsRecmGridRowMapper());
		return gridModels;
	}

	@Override
	public RmsRecmModel getRecm(String docCode, Integer docNo) throws Exception {
		String sql = "select * from rms_recm where SBU_CODE='450' and  DOC_CODE = '" + docCode + "' and DOC_NO = '" + docNo + "'";
		RmsRecmModel model = jdbcTemplate.queryForObject(sql, new RmsRecmRowMapper());
		return model;
	}

}
