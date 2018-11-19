package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InBillingTransactionRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ReFundAmntRowMapper;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
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
		List<InBillingTransactionsModel> billingTransactionsModels = jdbcTemplate.query("select a.*\r\n"
				+ "                 from inbillingtransactions a inner join \r\n" + "                 (select y.*\r\n"
				+ "                 from inbillingtransactions y  where sbucod='450' and pprnum='" + pprnum + "'\r\n"
				+ "                 group by sbucod,polnum,txnyer,txnmth having sum(amount) > 0) b\r\n"
				+ "                 on a.sbucod=b.sbucod and a.polnum=b.polnum and a.txnyer=b.txnyer and a.txnmth=b.txnmth \r\n"
				+ "                 \r\n" + "                 where a.sbucod='450' and a.doccod='PRMI' \r\n"
				+ "                 order by a.polnum,a.txnyer,a.txnmth", new InBillingTransactionRowMapper());
		return billingTransactionsModels;
	}

	@Override
	public InBillingTransactionsModel getTxnYearDate(String pprnum) throws Exception {
		InBillingTransactionsModel billingTransactionsModels = jdbcTemplate
				.queryForObject("select * from inbillingtransactions  where sbucod = '450' and pprnum = '" + pprnum
						+ "' order by creadt desc limit 1", new InBillingTransactionRowMapper());
		return billingTransactionsModels;
	}

	@Override
	public List<ReFundModel> getRefundList(String pprNum) throws Exception {
		List<ReFundModel> reFundModels = jdbcTemplate.query(
				"select pprnum,doccod,docnum,(sum(depost)*-1) as refamount, max(linnum) as linnum , paymod from inbillingtransactions where sbucod='450' and pprnum='"
						+ pprNum + "'  group by docnum having sum(depost) <0 order by docnum",
				new ReFundAmntRowMapper());
		return reFundModels;
	}

	@Override
	public Double paybleAmountThisMonth(Integer pprNo) throws Exception {
		Double amount = 0.0;

		try {
			List<Object> args = new ArrayList<>();
			args.add(pprNo);

			amount = jdbcTemplate.query(
					"SELECT sum(amount) `sum` FROM inbillingtransactions where sbucod = '450' and pprnum = ? and "
							+ "txnyer <= year(curdate()) and txnmth <= month(curdate())",
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
		InBillingTransactionsModel billingTransactionsModels = jdbcTemplate
				.queryForObject("select * from inbillingtransactions  where sbucod = '450' and pprnum = '" + pprnum
						+ "' and refdoc = 'PRMI' order by creadt desc limit 1", new InBillingTransactionRowMapper());
		return billingTransactionsModels;
	}

	@Override
	public List<InBillingTransactionsModel> getTransactionsByPprnum(String pprnum) throws Exception {
		
		return jdbcTemplate
				.query("select * from inbillingtransactions  where sbucod = '450' and pprnum = '" + pprnum
						+ "' ", new InBillingTransactionRowMapper());
	}

}
