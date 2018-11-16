package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.RmsDocTxnmCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.RmsDocTxnmGridRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.RmsDocTxnmRowMapper;
import org.arpico.groupit.receipt.model.RmsDocTxnmGridModel;
import org.arpico.groupit.receipt.model.RmsDocTxnmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RmsDocTxnmCustomDaoImpl implements RmsDocTxnmCustomDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RmsDocTxnmGridModel> findTop10(String creBy) throws Exception {
		
		String sql = "select REF1, CRE_DATE, DOC_CODE, amtfcu, DOC_NO  from rms_doc_txnm M WHERE SBU_CODE = '450' AND M.CRE_BY = '"+creBy+"' ORDER BY CRE_DATE DESC, DOC_NO DESC  LIMIT 10";
		
		List<RmsDocTxnmGridModel> gridModels = jdbcTemplate.query(sql, new RmsDocTxnmGridRowMapper());
		return gridModels;
	}

	@Override
	public RmsDocTxnmModel getModel(String docCode, Integer docNum) throws Exception {
		String sql = "select * from rms_doc_txnm WHERE SBU_CODE = '450' and DOC_CODE = '"+docCode+"' and DOC_NO = '"+docNum+"'";
		RmsDocTxnmModel docTxnmModel = jdbcTemplate.queryForObject(sql, new RmsDocTxnmRowMapper());
		return docTxnmModel;
	}
}
