package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.arpico.groupit.receipt.dao.ReceiptCancelationCustomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class ReceiptCancelationCustomDaoImpl implements ReceiptCancelationCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<String> findReceiptLikeReceiptId(String receiptId,String loccodes) throws Exception {
		List<String> receiptIdList = null;
		
		receiptIdList = jdbcTemplate.query("select docnum from intransactions where sbucod='450' and loccod in ("+loccodes+") and docnum like '"+receiptId+"%' ;", new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> receiptIdListTemp = new ArrayList<>();
				while(rs.next()) {
					String id=rs.getString("docnum");
					receiptIdListTemp.add(id);
				}
				return receiptIdListTemp;
			}
		});
		
		return receiptIdList;
	}


}
