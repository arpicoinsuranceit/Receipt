package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.RmsDocTxnmModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxnmModelPK;
import org.springframework.jdbc.core.RowMapper;

public class RmsDocTxnmRowMapper implements RowMapper<RmsDocTxnmModel> {

	@Override
	public RmsDocTxnmModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RmsDocTxnmModelPK modelPk = new RmsDocTxnmModelPK();

		modelPk.setDocCode(rs.getString("DOC_CODE"));
		modelPk.setDocNo(rs.getInt("DOC_NO"));
		modelPk.setLocCode(rs.getString("LOC_CODE"));
		modelPk.setSbuCode(rs.getString("SBU_CODE"));

		RmsDocTxnmModel model = new RmsDocTxnmModel();
		
		model.setRmsDocTxnmModelPK(modelPk);

		model.setAmtfcu(rs.getDouble("amtfcu"));
		model.setBatcno(rs.getDouble("BATCNO"));
		model.setBattyp(rs.getString("BATTYP"));
		model.setCnfuser(rs.getString("CONFUSER"));
		model.setConfirmd(rs.getString("CONFIRMD"));
		model.setCostcent(rs.getString("COSTCENT"));
		model.setCreBy(rs.getString("CRE_BY"));
		model.setCreDate(rs.getDate("CRE_DATE"));
		model.setCurrCode(rs.getString("CURR_CODE"));
		model.setCustSupCode(rs.getString("CUST_SUP_CODE"));
		model.setCustSupF(rs.getString("CUST_SUP_F"));
		model.setDeliDate(rs.getString("DELIDATE"));
		model.setDetlineSeq(rs.getInt("DET_LINE_SEQ"));
		model.setDownloaded(rs.getString("DOWNLOADED"));
		model.setExcrat(rs.getDouble("EXCRAT"));
		model.setFormInvloc(rs.getString("FROM_INVLOC"));
		model.setFromLoc(rs.getString("FROM_LOC"));
		model.setGlgrup(rs.getString("glgrup"));
		model.setGlupdt(rs.getString("GLUPDT"));
		model.setInvAmt(rs.getDouble("INV_AMT"));
		model.setIsuBy(rs.getString("ISU_BY"));
		model.setJobNo(rs.getString("JOB_NO"));
		model.setLocser(rs.getString("locser"));
		model.setModBy(rs.getString("MOD_BY"));
		model.setModDate(rs.getString("MOD_DATE"));
		model.setMstat(rs.getString("MSTAT"));
		model.setPassBy(rs.getString("PASS_BY"));
		model.setRecBy(rs.getString("REC_BY"));
		model.setRef1(rs.getString("REF1"));
		model.setRef2(rs.getString("REF2"));
		model.setRef3(rs.getString("REF3"));
		model.setRef4(rs.getString("REF4"));
		model.setRef5(rs.getString("REF5"));
		model.setRemarks(rs.getString("REMARKS"));
		model.setRepId(rs.getString("REP_ID"));
		model.setReqBy(rs.getString("REQ_BY"));
		model.setSetOffAmt(rs.getDouble("SET_OFF_AMT"));
		model.setSetOffAmtPd(rs.getDouble("SET_OFF_AMT_PD"));
		model.setTaxAmt1(rs.getDouble("TAX_AMT1"));
		model.setTaxAmt2(rs.getDouble("TAX_AMT2"));
		model.setToInvLoc(rs.getString("TO_INVLOC"));
		model.setToLoc(rs.getString("TO_LOC"));
		model.setTradis(rs.getDouble("TRADIS"));
		model.setTxnDate(rs.getDate("TXN_DATE"));
		model.setVourai(rs.getString("VOURAI"));
		
		return model;
	}

}
