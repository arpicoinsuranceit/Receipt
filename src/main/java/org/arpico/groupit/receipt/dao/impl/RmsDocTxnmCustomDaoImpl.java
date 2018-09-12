package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.RmsDocTxnmCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.RmsDocTxnmGridRowMapper;
import org.arpico.groupit.receipt.model.RmsDocTxnmGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RmsDocTxnmCustomDaoImpl implements RmsDocTxnmCustomDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RmsDocTxnmGridModel> findTop10(String creBy) throws Exception {
		
		String sql = "select REF1, CRE_DATE, DOC_CODE, amtfcu, DOC_NO  from marksys.rms_doc_txnm M WHERE M.CRE_BY = '"+creBy+"' ORDER BY CRE_DATE DESC, DOC_NO DESC  LIMIT 10";
		
		List<RmsDocTxnmGridModel> gridModels = jdbcTemplate.query(sql, new RmsDocTxnmGridRowMapper());
		return gridModels;
	}
}
