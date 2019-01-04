package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.DashboardDao;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardCashFlowSummeryRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardDetailsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardGridRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.DashboardPieRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.PayModeGridRowMapper;
import org.arpico.groupit.receipt.model.DashboardCashFlowSummeryModel;
import org.arpico.groupit.receipt.model.DashboardDetailsModel;
import org.arpico.groupit.receipt.model.DashboardGridModel;
import org.arpico.groupit.receipt.model.DashboardPieModel;
import org.arpico.groupit.receipt.model.PayModeGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardDaoImpl implements DashboardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DashboardPieModel> getFromInTransaction(String to, String from, String user) throws Exception {

		/*String query = "SELECT doccod, count(pprnum) as count, sum(totprm) as amount   FROM intransactions "
				+ "    where sbucod = '450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '" + user + "' "
				+ "and date_format(creadt,'%Y-%m-%d') between '" + from + "' and  '" + to + "' group by doccod";*/
		
		
		System.out.println("getFromInTransaction DashboardPieModel");
		
		String query = "select x.doccod, count(x.docnum) as count, sum(x.totprm) as amount   FROM intransactions x inner join  " + 
				"    (select sbucod,docnum,doccod from intransactions where sbucod='450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '"+user+"' and date_format(creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"' group by docnum) y " + 
				"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"' group by x.doccod " + 
				"    union all " + 
				"    select x.doccod, count(x.docnum) as count, sum(x.totprm) as amount   FROM inloantransactions x inner join  " + 
				"    (select sbucod,docnum,doccod from inloantransactions where sbucod='450' and doccod ='RCLN' and creaby = '"+user+"' and date_format(creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"' group by docnum) y " + 
				"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"' group by x.doccod;";

//		String sql = "SELECT doccod, count(pprnum) as count, sum(totprm) as amount FROM intransactions "
//				+ "where sbucod = '450' and creaby = '"
//				+ user + "' and creadt >= '" + from + "' and creadt <= '" + to
//				+ "' and doccod in ('RCNB','RCPP','RCPL') group by doccod";

		System.out.println(query);

		List<DashboardPieModel> models = jdbcTemplate.query(query, new DashboardPieRowMapper());
		return models;
	}

	@Override
	public List<DashboardPieModel> getFromRecm(String to, String from, String user) throws Exception {

		String query = "select DOC_CODE as doccod , count(DOC_NO) as count, sum(amtfcu) as amount from rms_recm "
				+ " where SBU_CODE = '450' and DOC_CODE = 'GLRC' and CRE_BY = '" + user + "' "
				+ " and CRE_DATE between '" + from + "' and '" + to + "'";

//		String sql = "select DOC_CODE as doccod , count(DOC_NO) as count, sum(amtfcu) as amount from rms_recm "
//				+ "where SBU_CODE = '450' and "
//				+ "DOC_CODE = 'GLRC' and CRE_BY = '" + user + "' and CRE_DATE <= '" + to + "' and CRE_DATE >= '" + from
//				+ "'";

		System.out.println(query);

		List<DashboardPieModel> models = jdbcTemplate.query(query, new DashboardPieRowMapper());
		return models;
	}

	@Override
	public List<DashboardPieModel> getFromDocTxnm(String to, String from, String user) throws Exception {

		String query = "select DOC_CODE as doccod, count(DOC_NO) as count, sum(amtfcu) as amount from rms_doc_txnd "
				+ " where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '" + user + "' "
				+ " and CRE_DATE between '" + from + "' and  '" + to + "'";

//		String sql = "select DOC_CODE as doccod, count(DOC_NO) as count, sum(amtfcu) as amount "
//				+ "from rms_doc_txnd where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '" + user + "' "
//				+ "and CRE_DATE <= '" + to + "' and CRE_DATE >= '" + from + "'";

		System.out.println(query);

		List<DashboardPieModel> models = jdbcTemplate.query(query, new DashboardPieRowMapper());
		return models;
	}

	@Override
	public List<DashboardGridModel> getFromInTransactionsGrid(String toDate, String fromDate, String user, String sql)
			throws Exception {

	/*	String query = "SELECT doccod, count(docnum) as count, sum(totprm)  as amount,  year(creadt) as year, month(creadt) as month,  day(creadt) as day FROM intransactions "
				+ " where sbucod = '450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '" + user + "' "
				+ " and date_format(creadt,'%Y-%m-%d') between '" + fromDate + "' and  '" + toDate + "' group by doccod"
				+ sql;*/
		
		String query = "select x.doccod, count(x.docnum) as count, sum(x.totprm) as amount,  year(x.creadt) as year, month(x.creadt) as month,  day(x.creadt) as day   FROM intransactions x inner join  " + 
				"    (select sbucod,docnum,doccod from intransactions where sbucod='450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '"+user+"' and date_format(creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' group by docnum) y " + 
				"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' group by x.doccod " + sql + 
				"    union all " + 
				"      select x.doccod, count(x.docnum) as count, sum(x.totprm) as amount,  year(x.creadt) as year, month(x.creadt) as month,  day(x.creadt) as day   FROM inloantransactions x inner join  " + 
				"    (select sbucod,docnum,doccod from inloantransactions where sbucod='450' and doccod ='RCLN' and creaby = '"+user+"' and date_format(creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' group by docnum) y " + 
				"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' group by x.doccod" + sql;

//		String query = "SELECT doccod, count(docnum) as count, sum(totprm)  as amount,  year(creadt) as year, month(creadt) as month,  day(creadt) as day FROM intransactions"
//				+ " where sbucod = '450' and creaby = '" + user + "' and creadt >= '" + fromDate + "' and creadt <= '"
//				+ toDate + "'  and doccod in ('RCNB','RCPP','RCPL') group by doccod" + sql;

		System.out.println(query);

		List<DashboardGridModel> models = jdbcTemplate.query(query, new DashboardGridRowMapper());
		return models;
	}

	@Override
	public List<DashboardGridModel> getFromRecmGrid(String toDate, String fromDate, String user, String sql)
			throws Exception {

		String query = "SELECT DOC_CODE as doccod, count(DOC_NO) as count, sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day  FROM rms_recm "
				+ "    where SBU_CODE = '450' and DOC_CODE IN ('GLRC') and CRE_BY = '" + user
				+ "' and CRE_DATE between '" + fromDate + "' and  '" + toDate
				+ "'  group by DOC_CODE, CRE_DATE order by CRE_DATE" + sql;

		/*
		 * String query =
		 * "SELECT DOC_CODE as doccod, count(DOC_NO) as count, sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day "
		 * + "FROM rms_recm where SBU_CODE = '450' and CRE_BY = '" + user +
		 * "' and CRE_DATE >= '" + fromDate + "' and CRE_DATE <= '" + toDate +
		 * "' and DOC_CODE in ('GLRC') " + "group by DOC_CODE" + sql;
		 */

		System.out.println(query);

		List<DashboardGridModel> models = jdbcTemplate.query(query, new DashboardGridRowMapper());
		return models;
	}

	@Override
	public List<DashboardGridModel> getFromTxnmGrid(String toDate, String fromDate, String user, String sql)
			throws Exception {

		String query = "SELECT DOC_CODE as doccod, count(DOC_NO) as count, sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day FROM rms_doc_txnm "
				+ "    where SBU_CODE = '450' and DOC_CODE in ('OIIS') and CRE_BY = '" + user
				+ "' and CRE_DATE between '" + fromDate + "' and  '" + toDate + "'  group by DOC_CODE" + sql;

		/*
		 * String query =
		 * "SELECT DOC_CODE as doccod, count(DOC_NO) as count, sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day "
		 * + "FROM rms_doc_txnm " + "where SBU_CODE = '450' and CRE_BY = '" + user +
		 * "' and CRE_DATE >= '" + fromDate + "' and CRE_DATE <= '" + toDate +
		 * "' and DOC_CODE in ('OIIS') " + "group by DOC_CODE" + sql;
		 */

		System.out.println(query);

		List<DashboardGridModel> models = jdbcTemplate.query(query, new DashboardGridRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getDashDetailsInTrans(String toDate, String fromDate, String user, String type)
			throws Exception {

		/*String query = "SELECT doccod as DOCCODE, docnum as DOCNUM, pprnum as REMARK, totprm as AMOUNT, creadt as CREATEDT  FROM intransactions "
				+ "    where sbucod = '450' and doccod = '" + type + "' and creaby = '" + user
				+ "' and date_format(creadt,'%Y-%m-%d') between '" + fromDate + "' and  '" + toDate
				+ "' order by creadt";*/
		String query = "";
		
		System.out.println(type);
		
		if(type.equals("RCLN")) {
			query = "select x.doccod as DOCCODE, x.docnum as DOCNUM, x.pprnum as REMARK, x.totprm as AMOUNT, x.creadt as CREATEDT   FROM inloantransactions x inner join  " + 
					"    (select sbucod,docnum,doccod from inloantransactions where sbucod='450' and doccod = '"+type+"' and creaby = '"+user+"' and date_format(creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' group by docnum) y " + 
					"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' order by x.creadt";
		} else {
			query = "select x.doccod as DOCCODE, x.docnum as DOCNUM, x.pprnum as REMARK, x.totprm as AMOUNT, x.creadt as CREATEDT   FROM intransactions x inner join  " + 
					"    (select sbucod,docnum,doccod from intransactions where sbucod='450' and doccod = '"+type+"' and creaby = '"+user+"' and date_format(creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' group by docnum) y " + 
					"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDate+"' order by x.creadt";
		}
		
		/*
		 * String query =
		 * "SELECT doccod as DOCCODE, docnum as DOCNUM, pprnum as REMARK, totprm as AMOUNT, creadt as CREATEDT  FROM intransactions"
		 * + " where sbucod = '450' and doccod = '" + type + "' and creaby = '" + user +
		 * "' and creadt >= '" + fromDate + "' and creadt <= '" + toDate + "'";
		 */

		System.out.println(query);

		List<DashboardDetailsModel> models = jdbcTemplate.query(query, new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getDashDetailsRecm(String toDate, String fromDate, String user, String type)
			throws Exception {

		String query = "SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARK as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT FROM rms_recm "
				+ "    where SBU_CODE = '450' and DOC_CODE = 'GLRC' and CRE_BY = '" + user + "' and CRE_DATE between '"
				+ fromDate + "' and  '" + toDate + "' order by CRE_DATE";

		/*
		 * String query =
		 * "SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARK as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT FROM rms_recm where SBU_CODE = '450' and DOC_CODE = 'GLRC' and CRE_BY = '"
		 * + user + "' and CRE_DATE > '" + fromDate + "' and CRE_DATE < '" + toDate +
		 * "'";
		 */

		System.out.println(query);

		List<DashboardDetailsModel> models = jdbcTemplate.query(query, new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getDashDetailsTxnm(String toDate, String fromDate, String user, String type)
			throws Exception {

		String query = "SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARKS as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT  FROM rms_doc_txnm "
				+ "    where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '" + user + "' and CRE_DATE between '"
				+ fromDate + "' and  '" + toDate + "' order by CRE_DATE";

//		String query = "SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARKS as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT  FROM rms_doc_txnm where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '"
//				+ user + "' and CRE_DATE > '" + fromDate + "' and CRE_DATE < '" + toDate + "'";

		System.out.println(query);

		List<DashboardDetailsModel> models = jdbcTemplate.query(query, new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardCashFlowSummeryModel> getCashFlowInTrans(String user, String to, String from)
			throws Exception {

		/*
		 * String query =
		 * "SELECT doccod as DOCCODE, count(docnum) as COUNT, sum(totprm) as AMOUNT, paymod as PAYMODE FROM intransactions "
		 * +
		 * "    where sbucod = '450' and doccod in ('RCNB', 'RCPP', 'RCPL') and creaby = '"
		 * + user + "' and date_format(creadt,'%Y-%m-%d') between '" + from + "' and  '"
		 * + to + "' " + "    group by doccod, paymod";
		 */
		
		System.out.println("getCashFlowDetails");

		String query2 = "select x.doccod as doccode, count(x.docnum) as count, sum(x.totprm) as amount,  x.paymod as PAYMODE   FROM intransactions x inner join  "
				+ "    (select sbucod,docnum,doccod from intransactions where sbucod='450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '"
				+ user + "' and date_format(creadt,'%Y-%m-%d') between '" + from + "' and  '" + to
				+ "' group by docnum) y "
				+ "    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '" + from + "' and  '" + to+"' group by x.doccod,x.paymod "
				+ "    union all "
				+ "      select x.doccod as doccode, count(x.docnum) as count, sum(x.totprm) as amount,  x.paymod as PAYMODE   FROM inloantransactions x inner join  "
				+ "    (select sbucod,docnum,doccod from inloantransactions where sbucod='450' and doccod ='RCLN' and creaby = '"
				+ user + "' and date_format(creadt,'%Y-%m-%d') between '" + from + "' and  '" + to
				+ "' group by docnum) y "
				+ "    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '" + from + "' and  '" + to+"' group by x.doccod,x.paymod";

//		String query = "SELECT doccod as DOCCODE, count(docnum) as COUNT, sum(totprm) as AMOUNT, paymod as PAYMODE FROM intransactions where sbucod = '450' and doccod in ('RCNB', 'RCPP', 'RCPL') and creaby = '"
//				+ user + "' and creadt >= '" + from + "' and creadt <= '" + to + "' group by doccod, paymod";

		System.out.println(query2);
		List<DashboardCashFlowSummeryModel> models = jdbcTemplate.query(query2,
				new DashboardCashFlowSummeryRowMapper());
		return models;
	}

	@Override
	public List<DashboardCashFlowSummeryModel> getCashFlowRecm(String user, String to, String from) throws Exception {

		String query = "SELECT rm.DOC_CODE as DOCCODE, count(rm.DOC_NO) as COUNT, sum(rm.AMTFCU) as AMOUNT, rd.PAY_MODE as PAYMODE "
				+ "    FROM rms_recm rm inner join rms_recd rd on rm.SBU_CODE=rd.SBU_CODE and rm.LOC_CODE=rd.LOC_CODE and rm.DOC_CODE = rd.DOC_CODE and rm.DOC_NO = rd.DOC_NO "
				+ "    where rm.SBU_CODE = '450' and rm.DOC_CODE in ('GLRC') and rm.CRE_BY = '" + user
				+ "' and rm.CRE_DATE between '" + from + "' and  '" + to + "' group by rm.DOC_CODE, rd.PAY_MODE";

//		String query = "SELECT rm.DOC_CODE as DOCCODE, count(rm.DOC_NO) as COUNT, sum(rm.AMTFCU) as AMOUNT, rd.PAY_MODE as PAYMODE  "
//				+ "FROM rms_recm rm, rms_recd rd where rm.SBU_CODE = '450' and rm.DOC_CODE in ('GLRC') and "
//				+ "rm.CRE_BY = '" + user + "' and rm.CRE_DATE >= '" + from + "' and rm.CRE_DATE <= '" + to
//				+ "' and rm.DOC_CODE = rd.DOC_CODE and " + "rm.DOC_NO = rd.DOC_NO group by rm.DOC_CODE, rd.PAY_MODE";

		System.out.println(query);
		List<DashboardCashFlowSummeryModel> models = jdbcTemplate.query(query, new DashboardCashFlowSummeryRowMapper());
		return models;
	}

	@Override
	public List<DashboardCashFlowSummeryModel> getCashFlowTxnm(String user, String to, String from) throws Exception {

		String query = "SELECT DOC_CODE as DOCCODE, count(DOC_NO) as COUNT, sum(AMTFCU) as AMOUNT, REF2 as PAYMODE  FROM rms_doc_txnm "
				+ "    where SBU_CODE = '450' and DOC_CODE in ('OIIS') and CRE_BY = '" + user
				+ "' and CRE_DATE between '" + from + "' and  '" + to + "' group by DOC_CODE, REF2";

//		String query = "SELECT DOC_CODE as DOCCODE, count(DOC_NO) as COUNT, sum(AMTFCU) as AMOUNT, REF2 as PAYMODE "
//				+ "FROM rms_doc_txnm where SBU_CODE = '450' and DOC_CODE in ('OIIS') and " + "CRE_BY = '" + user
//				+ "' and CRE_DATE >= '" + from + "' and CRE_DATE <= '" + to + "' group by DOC_CODE, REF2";

		System.out.println(query);

		List<DashboardCashFlowSummeryModel> models = jdbcTemplate.query(query, new DashboardCashFlowSummeryRowMapper());

		return models;
	}

	@Override
	public List<DashboardDetailsModel> getCashFlowGridInTrans(String to, String from, String user, String type)
			throws Exception {

		/*String query = "SELECT doccod as DOCCODE, docnum as DOCNUM, pprnum as REMARK, totprm as AMOUNT, creadt as CREATEDT  FROM intransactions "
				+ "    where sbucod = '450' and creaby = '" + user + "' and date_format(creadt,'%Y-%m-%d') between '"
				+ from + "' and  '" + to + "' and paymod = '" + type + "' order by creadt";*/
		
		String query = "select x.doccod as DOCCODE, x.docnum as DOCNUM, x.pprnum as REMARK, x.totprm as AMOUNT, x.creadt as CREATEDT   FROM intransactions x inner join  " + 
				"    (select sbucod,docnum,doccod from intransactions where sbucod='450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '" +user+ "' and date_format(creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"' and paymod = '"+type+"' group by docnum) y " + 
				"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"' " + 
				"    union all " + 
				"      select x.doccod as DOCCODE, x.docnum as DOCNUM, x.pprnum as REMARK, x.totprm as AMOUNT, x.creadt as CREATEDT  FROM inloantransactions x inner join  " + 
				"    (select sbucod,docnum,doccod from inloantransactions where sbucod='450' and doccod ='RCLN' and creaby = '"+user+"' and date_format(creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"' and paymod = '"+type+"' group by docnum) y " + 
				"    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"+from+"' and  '"+to+"'";

		/*
		 * String sql =
		 * "SELECT doccod as DOCCODE, docnum as DOCNUM, pprnum as REMARK, totprm as AMOUNT, creadt as CREATEDT  FROM intransactions"
		 * + " where sbucod = '450' and paymod = '" + type + "' and creaby = '" + user +
		 * "' and creadt >= '" + from + "' and creadt <= '" + to + "'";
		 */

		System.out.println(query);

		List<DashboardDetailsModel> models = jdbcTemplate.query(query, new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getCashFlowGridTxnm(String toDate, String fromDate, String user, String type)
			throws Exception {

		String query = "SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARKS as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT  FROM rms_doc_txnm "
				+ "    where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '" + user + "' and CRE_DATE between '"
				+ fromDate + "' and  '" + toDate + "' and REF2 in (" + type + ") order by CRE_DATE";

		/*
		 * String query =
		 * "SELECT DOC_CODE as DOCCODE, DOC_NO as DOCNUM, REMARKS as REMARK, AMTFCU as AMOUNT, CRE_DATE as CREATEDT  FROM rms_doc_txnm where SBU_CODE = '450' and DOC_CODE = 'OIIS' and CRE_BY = '"
		 * + user + "' and CRE_DATE > '" + fromDate + "' and CRE_DATE < '" + toDate +
		 * "' and REF2 in (" + type + ")";
		 */

		System.out.println(query);

		List<DashboardDetailsModel> models = jdbcTemplate.query(query, new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<DashboardDetailsModel> getCashFlowGridRecm(String toDate, String fromDate, String user, String type)
			throws Exception {

		String query = "SELECT rm.DOC_CODE as DOCCODE, rm.DOC_NO as DOCNUM, rm.REMARK as REMARK, rm.AMTFCU as AMOUNT, rm.CRE_DATE as CREATEDT "
				+ "    FROM rms_recm rm inner join rms_recd rd on rm.SBU_CODE=rd.SBU_CODE and rm.LOC_CODE=rd.LOC_CODE and rm.DOC_CODE = rd.DOC_CODE and rm.DOC_NO = rd.DOC_NO "
				+ "    where rm.SBU_CODE = '450' and rm.DOC_CODE = 'GLRC'  and rm.CRE_BY = '" + user
				+ "' and rm.CRE_DATE between '" + fromDate + "' and  '" + toDate + "' and rd.PAY_MODE = '" + type
				+ "' order by rm.CRE_DATE";

//		String sql = "SELECT rm.DOC_CODE as DOCCODE, rm.DOC_NO as DOCNUM, rm.REMARK as REMARK, rm.AMTFCU as AMOUNT, rm.CRE_DATE as CREATEDT FROM rms_recm rm, rms_recd rd "
//				+ "where rm.SBU_CODE = '450' and rm.DOC_CODE = 'GLRC' and rm.DOC_CODE = rd.DOC_CODE and rm.DOC_NO = rd.DOC_NO and rm.CRE_BY = '"
//				+ user + "' and rm.CRE_DATE > '" + fromDate + "' and rm.CRE_DATE < '" + toDate + "' and rd.PAY_MODE = '"
//				+ type + "'";

		System.out.println(query);

		List<DashboardDetailsModel> models = jdbcTemplate.query(query, new DashboardDetailsRowMapper());
		return models;
	}

	@Override
	public List<PayModeGridModel> getPayModeFromInTransactionsGrid(String toDateInTran, String fromDate, String user,
			String sql, String sql2) throws Exception {

		/*
		 * String query =
		 * "select sum(totprm) as amount, year(creadt) as year, month(creadt) as month,  day(creadt) as day, paymod FROM intransactions "
		 * +
		 * "    where sbucod = '450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '"
		 * + user + "' and date_format(creadt,'%Y-%m-%d') between '" + fromDate +
		 * "' and  '" + toDateInTran + "'   group by paymod" + sql;
		 */
//		String query = "select sum(totprm) as amount, year(creadt) as year, month(creadt) as month,  day(creadt) as day, paymod FROM intransactions "
//				+ "where sbucod = '450' and creaby = '" + user + "' and creadt >= '" + fromDate + "' "
//				+ "and creadt <= '" + toDateInTran + "'  and doccod in ('RCNB','RCPP','RCPL') group by paymod" + sql;

		String query = "select sum(y.amount) as amount ,y.year,y.month,y.day,y.paymod from (   "
				+ "    select sum(x.totprm) as amount, year(x.creadt) as year, month(x.creadt) as month,  day(x.creadt) as day, x.paymod as paymod   FROM intransactions x inner join   "
				+ "    (select sbucod,docnum,doccod from intransactions where sbucod='450' and doccod in ('RCNB','RCPP','RCPL') and creaby = '"
				+ user
				+ "' and date_format(creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDateInTran+"' group by docnum) y  "
				+ "    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"
				+ fromDate + "' and  '" + toDateInTran + "' group by x.paymod" + sql + "   " + "    union all  "
				+ "      select sum(x.totprm) as amount, year(x.creadt) as year, month(x.creadt) as month,  day(x.creadt) as day, x.paymod as paymod  FROM inloantransactions x inner join   "
				+ "    (select sbucod,docnum,doccod from inloantransactions where sbucod='450' and doccod ='RCLN' and creaby = '"
				+ user
				+ "' and date_format(creadt,'%Y-%m-%d') between '"+fromDate+"' and  '"+toDateInTran+"' group by docnum) y  "
				+ "    on x.sbucod=y.sbucod and x.docnum=y.docnum and x.doccod=y.doccod where date_format(x.creadt,'%Y-%m-%d') between '"
				+ fromDate + "' and  '" + toDateInTran + "' group by x.paymod " + sql + ")  y  group by y.paymod"
				+ sql2;

		System.out.println(query);

		List<PayModeGridModel> gridModels = jdbcTemplate.query(query, new PayModeGridRowMapper());

		return gridModels;
	}

	@Override
	public List<PayModeGridModel> getPayModeFromFromRecmGrid(String toDate, String fromDate, String user, String sql2)
			throws Exception {

		String query = "SELECT sum(rm.AMTFCU)  as amount, year(rm.CRE_DATE) as year, month(rm.CRE_DATE) as month,  day(rm.CRE_DATE) as day, rd.PAY_MODE as paymod  "
				+ "    FROM rms_recm rm inner join rms_recd rd on rm.SBU_CODE=rd.SBU_CODE and rm.LOC_CODE=rd.LOC_CODE and rm.DOC_CODE = rd.DOC_CODE and rm.DOC_NO = rd.DOC_NO "
				+ "    where rm.SBU_CODE = '450' and rm.DOC_CODE in ('GLRC') and rm.CRE_BY = '" + user
				+ "' and rm.CRE_DATE between '" + fromDate + "' and  '" + toDate + "' group by rd.PAY_MODE" + sql2;

//		String query = "SELECT sum(rm.AMTFCU)  as amount, year(rm.CRE_DATE) as year, month(rm.CRE_DATE) as month,  day(rm.CRE_DATE) as day, rd.PAY_MODE as paymod "
//				+ "FROM rms_recm rm, rms_recd rd where rm.SBU_CODE = '450' and rm.CRE_BY = '" + user
//				+ "' and rm.CRE_DATE >= '" + fromDate + "' and rm.CRE_DATE <= '" + toDate + "' and "
//				+ "rd.DOC_NO = rm.DOC_NO and rd.DOC_CODE = rm.DOC_CODE and rm.DOC_CODE in ('GLRC') group by rd.PAY_MODE"
//				+ sql2;

		System.out.println(query);

		List<PayModeGridModel> gridModels = jdbcTemplate.query(query, new PayModeGridRowMapper());

		return gridModels;
	}

	@Override
	public List<PayModeGridModel> getPayModeFromTxnmGrid(String toDate, String fromDate, String user, String sql2)
			throws Exception {

		String query = "SELECT sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day, REF2 as paymod FROM rms_doc_txnm "
				+ "    where SBU_CODE = '450' and DOC_CODE in ('OIIS') and CRE_BY = '" + user
				+ "' and CRE_DATE between '" + fromDate + "' and  '" + toDate + "' group by REF2" + sql2;

//		String query = "SELECT sum(AMTFCU)  as amount,  year(CRE_DATE) as year, month(CRE_DATE) as month,  day(CRE_DATE) as day, REF2 as paymod FROM rms_doc_txnm "
//				+ "where SBU_CODE = '450' and CRE_BY = '" + user + "' and CRE_DATE >= '" + fromDate
//				+ "' and CRE_DATE <=  '" + toDate + "' and DOC_CODE in ('OIIS') " + "group by REF2" + sql2;

		System.out.println(query);

		List<PayModeGridModel> gridModels = jdbcTemplate.query(query, new PayModeGridRowMapper());

		return gridModels;
	}

}
