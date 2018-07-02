package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.LastReceiptRowMapper;
import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InTransactionCustomDaoImpl implements InTransactionCustomDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<LastReceiptSummeryModel> getLastReceipts(String user) throws Exception {
		return (List<LastReceiptSummeryModel>) jdbcTemplate.query("select doccod, docnum, creadt, pprnum, polnum, totprm from intransactions where creaby "
				+ " = '"+user+"' order by creadt desc limit 8", new LastReceiptRowMapper());
	}

}
