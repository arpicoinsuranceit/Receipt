package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InBillingTransactionRowMapper implements RowMapper<InBillingTransactionsModel>{

	@Override
	public InBillingTransactionsModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		InBillingTransactionsModelPK modelPK = new InBillingTransactionsModelPK();
		modelPK.setDoccod(rs.getString("doccod"));
		modelPK.setDocnum(rs.getInt("docnum"));
		modelPK.setLinnum(rs.getInt("linnum"));
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setSbucod(rs.getString("sbucod"));
		modelPK.setTxndat(rs.getDate("txndat"));
		
		InBillingTransactionsModel model = new InBillingTransactionsModel();
		model.setBillingTransactionsModelPK(modelPK);
		
		model.setAdmfee(rs.getDouble("admfee"));
		model.setAdvcod(rs.getInt("advcod"));
		model.setAgncls(rs.getString("agncls"));
		model.setAmount(rs.getDouble("amount"));
		model.setBatcno(rs.getInt("batcno"));
		model.setBattyp(rs.getString("battyp"));
		model.setBrncod(rs.getString("brncod"));
		model.setCandoc(rs.getString("candoc"));
		model.setChqrel(rs.getString("chqrel"));
		model.setComiss(rs.getDouble("comiss"));
		model.setComper(rs.getDouble("comper"));
		model.setCreaby(rs.getString("creaby"));
		model.setCreadt(rs.getDate("creadt"));
		model.setCscode(rs.getInt("cscode"));
		model.setDepost(rs.getDouble("depost"));
		model.setDuedat(rs.getDate("duedat"));
		model.setGlintg(rs.getString("glintg"));
		model.setGrsprm(rs.getDouble("grsprm"));
		model.setHrbprm(rs.getDouble("hrbprm"));
		model.setIcpmon(rs.getInt("icpmon"));
		model.setIcpyer(rs.getInt("icpyer"));
		model.setInsnum(rs.getInt("insnum"));
		model.setLockin(rs.getDate("lockin"));
		model.setOldprm(rs.getDouble("oldprm"));
		model.setOtham1(rs.getDouble("otham1"));
		model.setOtham2(rs.getDouble("otham2"));
		model.setOtham3(rs.getDouble("otham3"));
		model.setOtham4(rs.getDouble("otham4"));
		model.setPaymod(rs.getString("paymod"));
		model.setPaytrm(rs.getInt("paytrm"));
		model.setPolfee(rs.getDouble("polfee"));
		model.setPolnum(rs.getInt("polnum"));
		model.setPolyer(rs.getInt("polyer"));
		model.setPprnum(rs.getInt("pprnum"));
		model.setPrcyer(rs.getInt("prcyer"));
		model.setPrdcod(rs.getString("prdcod"));
		model.setPrpseq(rs.getInt("prpseq"));
		model.setRefdoc(rs.getString("refdoc"));
		model.setRefnum(rs.getInt("refnum"));
		model.setSrcdoc(rs.getString("srcdoc"));
		model.setSrcnum(rs.getInt("srcnum"));
		model.setTaxamt(rs.getDouble("taxamt"));
		model.setToptrm(rs.getInt("toptrm"));
		model.setTxnbno(rs.getInt("txnbno"));
		model.setTxnmth(rs.getInt("txnmth"));
		model.setTxntyp(rs.getString("txntyp"));
		model.setTxnyer(rs.getInt("txnyer"));
		model.setUnlcod(rs.getString("unlcod"));
		
		return model;
	}

}
