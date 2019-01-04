package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.arpico.groupit.receipt.dao.ReceiptCancelationCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.CanceledReceiptRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InLoanTransactionRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InTransactionRowMapper;
import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
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
	public List<String> findReceiptLikeReceiptId(String receiptId, String loccodes, boolean isHo) throws Exception {
		List<String> receiptIdList = null;
<<<<<<< HEAD
		String sql = "";

		if (isHo) {
			sql = "select docnum from intransactions where sbucod='450' and docnum like '" + receiptId + "%' ";
		} else {
			sql = "select docnum from intransactions where sbucod='450' and loccod in (" + loccodes
					+ ") and docnum like '" + receiptId + "%' ";
=======
		String sql="";
		
		if(isHo) {
			sql="select docnum from intransactions where sbucod='450' and docnum like '"+receiptId+"%' union all select docnum from inloantransactions where sbucod='450' and docnum like '"+receiptId+"%'";
		}else {
			sql="select docnum from intransactions where sbucod='450' and loccod in ("+loccodes+") and docnum like '"+receiptId+"%' union all select docnum from inloantransactions where sbucod='450' and loccod in ("+loccodes+") and docnum like '"+receiptId+"%'";
>>>>>>> origin/feature-changes-v3
		}

		receiptIdList = jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> receiptIdListTemp = new ArrayList<>();
				while (rs.next()) {
					String id = rs.getString("docnum");
					receiptIdListTemp.add(id);
				}
				return receiptIdListTemp;
			}
		});

		return receiptIdList;
	}

	@Override
	public List<CanceledReceiptModel> findPendingRequest(String loccodes, String status, boolean isHo)
			throws Exception {
		List<CanceledReceiptModel> canceledReceiptModels = null;

		String sql = "select sbucod,loccod,polnum,pprnum,reason,docnum,rqstby,rqstdt,status,amount,doccod from inreceiptauth where sbucod='450' and loccod in ("
				+ loccodes + ") and status='" + status + "' order by rqstdt desc";

		if (isHo) {
			sql = "select sbucod,loccod,polnum,pprnum,reason,docnum,rqstby,rqstdt,status,amount,doccod from inreceiptauth where sbucod='450' and status='"
					+ status + "' order by rqstdt desc";
		}

		canceledReceiptModels = jdbcTemplate.query(sql, new CanceledReceiptRowMapper());

		return canceledReceiptModels;
	}

	@Override
	public String findGMEmail(String sbucode, String loccode) throws Exception {

		String email = jdbcTemplate.queryForObject("select ig.email from ingmzone ig "
				+ "inner join inzonemast z on ig.zoncod=z.zoncod and ig.sbucod=z.sbucod "
				+ "inner join inregion r on z.zoncod=r.zoncod and z.sbucod=r.sbucod "
				+ "inner join rms_locations l on r.rgncod=l.rgncod and r.sbucod=l.sbu_code " + "where l.sbu_code='"
				+ sbucode + "' and  l.loccod='" + loccode + "' ", String.class);

		return email;
	}

	@Override
	public InTransactionsModel findTransctionRow(String sbucode, String docnum,String doccod,String creby,boolean isHo) throws Exception {
		InTransactionsModel transaction=null;
		if(isHo) {
			transaction=jdbcTemplate.queryForObject("SELECT * FROM intransactions where sbucod='"+sbucode+"' and docnum='"+docnum+"' and doccod='"+doccod+"'  having sum(totprm) > 0 ", new InTransactionRowMapper());
		}else {
			transaction=jdbcTemplate.queryForObject("SELECT * FROM intransactions where sbucod='"+sbucode+"' and docnum=(SELECT docnum FROM intransactions where sbucod='"+sbucode+"' and docnum='"+docnum+"' and doccod='"+doccod+"' and creaby='"+creby+"') and doccod='"+doccod+"'  having sum(totprm) > 0 ", new InTransactionRowMapper());
		}
		
		
		return transaction;
	}
	
	@Override
	public InLoanTransactionsModel findLoanTransctionRow(String sbucode, String docnum,String doccod,String creby,boolean isHo) throws Exception {
		InLoanTransactionsModel transaction=null;
		if(isHo) {
			transaction=jdbcTemplate.queryForObject("SELECT * FROM inloantransactions where sbucod='"+sbucode+"' and docnum='"+docnum+"' and doccod='"+doccod+"'  having sum(totprm) > 0 ", new InLoanTransactionRowMapper());
		}else {
			transaction=jdbcTemplate.queryForObject("SELECT * FROM inloantransactions where sbucod='"+sbucode+"' and docnum=(SELECT docnum FROM inloantransactions where sbucod='"+sbucode+"' and docnum='"+docnum+"' and doccod='"+doccod+"' and creaby='"+creby+"') and doccod='"+doccod+"'  having sum(totprm) > 0 ", new InLoanTransactionRowMapper());
		}
		
		
		return transaction;
	}

	@Override
	public List<CanceledReceiptModel> findPendingRequest(String locations, String status, boolean isHo, Integer page,
			Integer offset) throws Exception {
		List<CanceledReceiptModel> canceledReceiptModels = null;

		String sql = "select sbucod,loccod,polnum,pprnum,reason,docnum,rqstby,rqstdt,status,amount,doccod from inreceiptauth where sbucod='450' and loccod in ("
				+ locations + ") and status='" + status + "' order by rqstdt desc limit " + page + ", " + offset;

		if (isHo) {
			sql = "select sbucod,loccod,polnum,pprnum,reason,docnum,rqstby,rqstdt,status,amount,doccod from inreceiptauth where sbucod='450' and status='"
					+ status + "' order by rqstdt desc limit " + page + ", " + offset;
		}

		canceledReceiptModels = jdbcTemplate.query(sql, new CanceledReceiptRowMapper());

		return canceledReceiptModels;
	}

	@Override
	public Integer findPendingRequestLength(String locations, String status, boolean isHo) throws Exception {
		String sql = "select count(rqstby) from inreceiptauth where sbucod='450' and loccod in (" + locations
				+ ") and status='" + status + "' order by rqstdt desc ";

		if (isHo) {
			sql = "select count(rqstby) from inreceiptauth where sbucod='450' and status='" + status
					+ "' order by rqstdt desc";
		}

		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

}
