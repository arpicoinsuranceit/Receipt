package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.RmsRecmModel;
import org.arpico.groupit.receipt.model.pk.RmsRecmModelPK;
import org.springframework.jdbc.core.RowMapper;

public class RmsRecmRowMapper implements RowMapper<RmsRecmModel>{

	@Override
	public RmsRecmModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RmsRecmModelPK pk = new RmsRecmModelPK();
		pk.setDocCode(rs.getString("DOC_CODE"));
		pk.setDocNo(rs.getInt("DOC_NO"));
		pk.setLocCode(rs.getString("LOC_CODE"));
		pk.setSbuCode(rs.getString("SBU_CODE"));
		
		
		RmsRecmModel model = new RmsRecmModel();
		
		model.setRmsRecmModelPK(pk);
		model.setAmtfcu(rs.getDouble("AMTFCU"));
		model.setBatcno(rs.getDouble("BATCNO"));
		model.setBattyp(rs.getString("BATTYP"));
		model.setCreBy(rs.getString("CRE_BY"));
		model.setCreDate(rs.getDate("CRE_DATE"));
		model.setCscode(rs.getString("CS_CODE"));
		model.setCurrCode(rs.getString("CURR_CODE"));
		model.setCustSup(rs.getString("CUST_SUP"));
		model.setExcrat(rs.getString("EXCRAT"));
		model.setGlgrup(rs.getString("glgrup"));
		model.setGlupdt(rs.getString("GLUPDT"));
		model.setJobNo(rs.getString("JOB_NO"));
		model.setModBy(rs.getString("MOD_BY"));
		//model.setModDate(rs.getString("MOD_DATE"));
		model.setMstat(rs.getString("MSTAT"));
		model.setOldRemark(rs.getString("old_remark"));
		model.setPdBal(rs.getDouble("PD_BAL"));
		model.setPdChq(rs.getString("PD_CHQ"));
		model.setRecAmt(rs.getDouble("REC_AMT"));
		model.setRecBal(rs.getDouble("REC_BAL"));
		model.setRemark(rs.getString("REMARK"));
		model.setRepId(rs.getString("REP_ID"));
		model.setRepManFlg(rs.getString("REP_MAN_FLG"));
		model.setSysupload(rs.getString("sysupload"));
		model.setTxnDate(rs.getDate("TXN_DATE"));
		
		return model;
	}

}
