package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.DashboardDao;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardCashFlowSummeryRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardDetailsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardGridRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardPieRowMapper;
import org.arpico.groupit.receipt.model.DashboardCashFlowSummeryModel;
import org.arpico.groupit.receipt.model.DashboardDetailsModel;
import org.arpico.groupit.receipt.model.DashboardGridModel;
import org.arpico.groupit.receipt.model.DashboardPieModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardDaoImpl implements DashboardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DashboardPieModel> getFromInTransaction(String to, String from, String user) throws Exception {
		List<DashboardPieModel> models = jdbcTemplate.query(
				"SELECT doccod, count(pprnum) as count, sum(totprm) as amount FROM marksys.intransactions where sbucod = '450' and creaby = '"
						+ user + "' and creadt >= '" + from + "' and creadt <= '" + to
						+ "' and doccod in ('RCNB','RCPP','RCPL') group by doccod",
				new DashboardPieRowMapper());
		return models;
	}

	@Override
	public List<DashboardPieModel> getFromRecm(String to, String from, String user) throws Exception {
		List<DashboardPieModel> models = jdbcTemplate.query(
				"select DOC_CODE as doccod , count(DOC_NO) as count, sum(amtfcu) as amount from rms_recm where SBU_CODE = '450' and "
						+ "DOC_CODE = 'GLRC' and CRE_BY = '" + user + "' and CRE_DATE <= '" + to + "' and CRE_DATE >= '"
						+ from + "'",
				new DashboardPieRowMapper());
		return models;
	}

	@Override
	public List<DashboardPieModel> getFromDocTxnm(String to, String from, String user) throws Exception {
		List<DashboardPieModel> models = jdbcTemplate.query(
				"select DOC_CODE as doccod, count(DOC_NO) as count, sum(amtfcu) as amount "
						+ "from marksys.rms_doc_txnd where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '" + user
						+ "' " + "and CRE_DATE <= '" + to + "' and CRE_DATE >= '" + from + "'",
				new DashboardPieRowMapper());
		return models;
	}

	@Override
	public List<DashboardGridModel> getFromInTransactionsGrid(String toDate, String fromDate, String user, String sql)
			throws Exception {
		List<DashboardGridModel> models = jdbcTemplate.query(
				"SELECT doccod, count(docnum) as count, sum(totprm)  as amount,  year(creadt) as year, month(creadt) as month,  day(creadt) as day FROM marksys.intransactions"
						+ " where sbucod = '450' and creaby = '" + user + "' and creadt >= '" + fromDate
						+ "' and creadt <= '" + toDate + "'  and doccod in ('RCNB','RCPP','RCPL') group by doccod"
						+ sql,
				new DashboardGridRowMapper());
		return models;
	}

	@Override
	public List<DashboardGridModel> getFromRecmGrid(String toDate, String fromDate, String user, String sql)
			throws Exception {
		List<DashboardGridModel> models = jdbcTemplate.query(
				"SELECT DOC_CODE as doccod, count(DOC_NO) as count, sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day "
						+ "FROM marksys.rms_recm where SBU_CODE = '450' and CRE_BY = '" + user + "' and CRE_DATE >= '"
						+ fromDate + "' and CRE_DATE <= '" + toDate + "' and DOC_CODE in ('GLRC') "
						+ "group by DOC_CODE" + sql,
				new DashboardGridRowMapper());
		return models;
	}

	@Override
	public List<DashboardGridModel> getFromTxnmGrid(String toDate, String fromDate, String user, String sql)
			throws Exception {
		List<DashboardGridModel> models = jdbcTemplate.query(
				"SELECT DOC_CODE as doccod, count(DOC_NO) as count, sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day "
						+ "FROM marksys.rms_doc_txnd where SBU_CODE = '450' and CRE_BY = '" + user
						+ "' and CRE_DATE >= '" + fromDate + "' and CRE_DATE <= '" + toDate
						+ "' and DOC_CODE in ('OIIS') " + "group by DOC_CODE" + sql,
				new DashboardGridRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getDashDetailsInTrans(String toDate, String fromDate, String user, String type)
			throws Exception {
		List<DashboardDetailsModel> models = jdbcTemplate.query(
				"SELECT doccod as DOCCODE, docnum as DOCNUM, pprnum as REMARK, totprm as AMOUNT, creadt as CREATEDT  FROM marksys.intransactions"
						+ " where sbucod = '450' and doccod = '" + type + "' and creaby = '" + user
						+ "' and creadt >= '" + fromDate + "' and creadt <= '" + toDate + "'",
				new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getDashDetailsRecm(String toDate, String fromDate, String user, String type)
			throws Exception {
		List<DashboardDetailsModel> models = jdbcTemplate.query(
				"SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARK as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT FROM marksys.rms_recm where SBU_CODE = '450' and DOC_CODE = 'GLRC' and CRE_BY = '"
						+ user + "' and CRE_DATE > '" + fromDate + "' and CRE_DATE < '" + toDate + "'",
				new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getDashDetailsTxnm(String toDate, String fromDate, String user, String type)
			throws Exception {
		List<DashboardDetailsModel> models = jdbcTemplate.query(
				"SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARKS as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT  FROM marksys.rms_doc_txnm where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '"
						+ user + "' and CRE_DATE > '" + fromDate + "' and CRE_DATE < '" + toDate + "'",
				new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardCashFlowSummeryModel> getCashFlowInTrans(String user, String to, String from)
			throws Exception {
		List<DashboardCashFlowSummeryModel> models = jdbcTemplate.query(
				"SELECT doccod as DOCCODE, count(docnum) as COUNT, sum(totprm) as AMOUNT, paymod as PAYMODE FROM marksys.intransactions where sbucod = '450' and doccod in ('RCNB', 'RCPP', 'RCPL') and creaby = '"
						+ user + "' and creadt >= '" + from + "' and creadt <= '" + to + "' group by doccod, paymod",
				new DashboardCashFlowSummeryRowMapper());
		return models;
	}

	@Override
	public List<DashboardCashFlowSummeryModel> getCashFlowRecm(String user, String to, String from) throws Exception {
		List<DashboardCashFlowSummeryModel> models = jdbcTemplate
				.query("SELECT rm.DOC_CODE as DOCCODE, count(rm.DOC_NO) as COUNT, sum(rm.AMTFCU) as AMOUNT, rd.PAY_MODE as PAYMODE \r\n" + 
						"FROM marksys.rms_recm rm, marksys.rms_recd rd where rm.SBU_CODE = '450' and rm.DOC_CODE in ('GLRC') and\r\n" + 
						"rm.CRE_BY = '"+user+"' and rm.CRE_DATE >= '"+from+"' and rm.CRE_DATE <= '"+to+"' and rm.DOC_CODE = rd.DOC_CODE and\r\n" + 
						"rm.DOC_NO = rd.DOC_NO group by rm.DOC_CODE, rd.PAY_MODE", new DashboardCashFlowSummeryRowMapper());
		return models;
	}

	@Override
	public List<DashboardCashFlowSummeryModel> getCashFlowTxnm(String user, String to, String from) throws Exception {
		List<DashboardCashFlowSummeryModel> models = jdbcTemplate
				.query("SELECT DOC_CODE as DOCCODE, count(DOC_NO) as COUNT, sum(AMTFCU) as AMOUNT, REF2 as PAYMODE "
						+ "FROM marksys.rms_doc_txnm where SBU_CODE = '450' and DOC_CODE in ('OIIS') and "
						+ "CRE_BY = '" + user + "' and CRE_DATE >= '" + from + "' and CRE_DATE <= '" + to
						+ "' group by DOC_CODE, REF2", new DashboardCashFlowSummeryRowMapper());
		
		
		return models;
	}

}
