package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.RmsDocTxndCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.RmsDocTxndRowMapper;
import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RmsDocTxndCustomDaoImpl implements RmsDocTxndCustomDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RmsDocTxndModel> getDocTxndModels(String docCode, Integer docNum) throws Exception {
		List<RmsDocTxndModel> models = jdbcTemplate.query("select * from rms_doc_txnd WHERE DOC_CODE = '"+docCode+"' and DOC_NO = '"+docNum+"'", new RmsDocTxndRowMapper());
		return models;
	}

	
}
