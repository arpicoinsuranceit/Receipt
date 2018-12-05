package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InBillingTransactionRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.PaymentHistoryRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ReFundAmntRowMapper;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.PaymentHistoryModel;
import org.arpico.groupit.receipt.model.ReFundModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class InBillingTransactionsCustomDaoImpl implements InBillingTransactionsCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InBillingTransactionsModel> getUnSetOffs(String pprnum) throws Exception {
//		List<InBillingTransactionsModel> billingTransactionsModels = jdbcTemplate.query("select a.* "
//				+ "                 from inbillingtransactions a inner join  " + "                 (select y.* "
//				+ "                 from inbillingtransactions y  where sbucod='450' and pprnum='" + pprnum + "' "
//				+ "                 group by sbucod,polnum,txnyer,txnmth having sum(amount) > 0) b "
//				+ "                 on a.sbucod=b.sbucod and a.polnum=b.polnum and a.txnyer=b.txnyer and a.txnmth=b.txnmth  "
//				+ "                  " + "                 where a.sbucod='450' and a.doccod='PRMI'  "
//				+ "                 order by a.polnum,a.txnyer,a.txnmth", new InBillingTransactionRowMapper());
//		

		List<InBillingTransactionsModel> billingTransactionsModels = jdbcTemplate.query(
				"select a.* from inbillingtransactions a inner join "
						+ "    (select y.* from inbillingtransactions y  where sbucod='450' and pprnum='" + pprnum
						+ "' group by sbucod,polnum,txnyer,txnmth having sum(amount) > 0) b "
						+ "    on a.sbucod=b.sbucod and a.pprnum=b.pprnum and a.txnyer=b.txnyer and a.txnmth=b.txnmth  "
						+ "    where a.sbucod='450' and a.doccod='PRMI' order by a.polnum,a.txnyer,a.txnmth;",
				new InBillingTransactionRowMapper());

		return billingTransactionsModels;
	}

	@Override
	public InBillingTransactionsModel getTxnYearDate(String pprnum) throws Exception {
		/*
		 * InBillingTransactionsModel billingTransactionsModels = jdbcTemplate
		 * .queryForObject("select * from inbillingtransactions  where sbucod = '450' and pprnum = '"
		 * + pprnum + "' order by creadt desc limit 1", new
		 * InBillingTransactionRowMapper());
		 */

		InBillingTransactionsModel billingTransactionsModels = jdbcTemplate
				.queryForObject("select * from inbillingtransactions  " + "where sbucod = '450' and pprnum = '" + pprnum
						+ "' order by creadt desc limit 1", new InBillingTransactionRowMapper());

		return billingTransactionsModels;
	}

	@Override
	public List<ReFundModel> getRefundList(String pprNum) throws Exception {
//		List<ReFundModel> reFundModels = jdbcTemplate.query(
//				"select pprnum,doccod,docnum,(sum(depost)*-1) as refamount, max(linnum) as linnum , paymod from inbillingtransactions where sbucod='450' and pprnum='"
//						+ pprNum + "'  group by docnum having sum(depost) <0 order by docnum",
//				new ReFundAmntRowMapper());

		List<ReFundModel> reFundModels = jdbcTemplate.query(
				"select pprnum,doccod,docnum,(sum(depost)*-1) as refamount, max(linnum) as linnum , paymod from inbillingtransactions "
						+ "where sbucod='450' and pprnum='" + pprNum
						+ "'  group by docnum having sum(depost) <0 order by docnum",
				new ReFundAmntRowMapper());
		return reFundModels;
	}

	@Override
	public Double paybleAmountThisMonth(Integer pprNo) throws Exception {
		Double amount = 0.0;

		try {
			List<Object> args = new ArrayList<>();
			args.add(pprNo);

//			amount = jdbcTemplate.query(
//					"SELECT sum(amount) `sum` FROM inbillingtransactions where sbucod = '450' and pprnum = ? and "
//							+ "txnyer <= year(curdate()) and txnmth <= month(curdate())",
//					args.toArray(), new ResultSetExtractor<Double>() {
//
//						@Override
//						public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
//							Double amountTemp = 0.0;
//							if (rs.next()) {
//								amountTemp = rs.getDouble("sum");
//							}
//							return amountTemp;
//						}
//					});

			amount = jdbcTemplate.query("SELECT sum(amount) `sum` FROM inbillingtransactions "
					+ " where sbucod = '450' and pprnum=? and  txnyer <= year(curdate()) and txnmth <= month(curdate())",
					args.toArray(), new ResultSetExtractor<Double>() {

						@Override
						public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
							Double amountTemp = 0.0;
							if (rs.next()) {
								amountTemp = rs.getDouble("sum");
							}
							return amountTemp;
						}
					});
		} catch (Exception e) {
			// TODO: handle exception
		}

		return amount;
	}

	@Override
	public InBillingTransactionsModel getLasiInvoice(String pprnum) throws Exception {
//		InBillingTransactionsModel billingTransactionsModels = jdbcTemplate
//				.queryForObject(
//						"select * from inbillingtransactions  where sbucod = '450' and pprnum = '" + pprnum
//								+ "' and refdoc = 'PRMI' order by creadt desc limit 1",
//						new InBillingTransactionRowMapper());

		InBillingTransactionsModel billingTransactionsModels = jdbcTemplate
				.queryForObject(
						"select * from inbillingtransactions  where sbucod = '450' and pprnum = '" + pprnum
								+ "' and refdoc = 'PRMI' order by creadt desc limit 1",
						new InBillingTransactionRowMapper());

		return billingTransactionsModels;
	}

	@Override
	public List<InBillingTransactionsModel> getTransactionsByPprnum(String pprnum) throws Exception {

		return jdbcTemplate.query(
				"select * from inbillingtransactions  where sbucod = '450' and pprnum = '" + pprnum + "' ",
				new InBillingTransactionRowMapper());
	}

	@Override
	public List<PaymentHistoryModel> getPaymentHistory(String pprNum) throws Exception {
		return jdbcTemplate.query(
				"select txnyer,txnmth,max(txndat) txndat,sum(if(doccod <> 'PRMI',amount,0)) setamt,sum(if(doccod = 'PRMI',amount,0)) dueamt,max(duedat) duedat,  "
						+ "						sum(if(doccod = 'PRMI',amount,0))+sum(if(doccod <> 'PRMI',amount,0)) outstd,  "
						+ "						ifnull((select group_concat(docnum) docnum from inbillingtransactions b  "
						+ "						where a.sbucod=b.sbucod and a.pprnum=b.pprnum and a.txnyer=b.txnyer  "
						+ "						and a.txnmth=b.txnmth and doccod <> 'PRMI' and amount <> 0 and txntyp <> 'RECOVERY' group by txnyer,txnmth),'') remark   "
						+ "						from inbillingtransactions a where sbucod='450' and pprnum= '" + pprNum
						+ "						' and amount <> 0 group by txnyer desc,txnmth desc",
				new PaymentHistoryRowMapper());
	}

}
