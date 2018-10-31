package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxndModelPK;
import org.springframework.jdbc.core.RowMapper;

public class RmsDocTxndRowMapper implements RowMapper<RmsDocTxndModel> {

	@Override
	public RmsDocTxndModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RmsDocTxndModelPK pk = new RmsDocTxndModelPK();
		
		pk.setDocCode(rs.getString("DOC_CODE"));
		pk.setDocNo(rs.getInt("DOC_NO"));
		pk.setLocCode(rs.getString("LOC_CODE"));
		pk.setSbuCode(rs.getString("SBU_CODE"));
		pk.setSeqNo(rs.getInt("SEQ_NO"));
		
		RmsDocTxndModel model = new RmsDocTxndModel();
		
		model.setRmsDocTxndModelPK(pk);
		
		model.setAmtfcu(rs.getDouble("amtfcu"));
		model.setAvgpri(rs.getDouble("AVG_PRI"));
		model.setCreateBy(rs.getString("CRE_BY"));
		model.setCreDate(rs.getDate("CRE_DATE"));
		model.setDescri(rs.getString("descri"));
		model.setDimm04(rs.getString("dimm04"));
		model.setDimm05(rs.getString("dimm05"));
		model.setDimm06(rs.getString("dimm06"));
		model.setDimm07(rs.getString("dimm07"));
		model.setDimm08(rs.getString("dimm08"));
		model.setDimm09(rs.getString("dimm09"));
		model.setDimm10(rs.getString("dimm10"));
		model.setDiscReimberse(rs.getDouble("disc_reimberse"));
		model.setDisrate(rs.getDouble("DISRATE"));
		model.setDstat(rs.getString("DSTAT"));
		model.setEnecst(rs.getDouble("enecst"));
		model.setFixovh(rs.getDouble("fixovh"));
		model.setGlgrup(rs.getString("glgrup"));
		model.setInterid(rs.getDouble("interid"));
		model.setIssuedQty(rs.getDouble("ISSUED_QTY"));
		model.setItemCode(rs.getString("ITEM_CODE"));
		model.setItemGroup(rs.getString("itmgrp"));
		model.setJobHid(rs.getInt("job_hid"));
		model.setJobNo(rs.getString("job_no"));
		model.setJobSeq(rs.getInt("jobseq"));
		model.setJobTyp(rs.getString("jobtyp"));
		model.setLabCst(rs.getString("labcst"));
		model.setLotnum(rs.getDouble("lotnum"));
		model.setMatcst(rs.getDouble("matcst"));
		model.setMod_by(rs.getString("MOD_BY"));
		model.setMod_date(rs.getDate("MOD_DATE"));
		model.setPluCode(rs.getString("PLU_CODE"));
		model.setPosNo(rs.getInt("POS_NO"));
		model.setPrcflg(rs.getString("prcflg"));
		model.setPrice(rs.getDouble("PRICE"));
		model.setProfiteRate(rs.getDouble("PROFITRATE"));
		model.setQty(rs.getDouble("QTY"));
		model.setReaCode(rs.getString("REA_CODE"));
		model.setRemark(rs.getString("REMARK"));
		model.setSourceDocCode(rs.getString("SOURCE_DOC_CODE"));
		model.setSourceDocNo(rs.getInt("SOURCE_DOC_NO"));
		model.setStdcst(rs.getDouble("stdcst"));
		model.setStockLoc(rs.getString("STOCK_LOC"));
		model.setSysupload(rs.getString("sysupload"));
		model.setTaxAmt1(rs.getDouble("TAX_AMT1"));
		model.setTaxAmt2(rs.getDouble("TAX_AMT2"));
		model.setTaxAmt3(rs.getDouble("TAX_AMT3"));
		model.setTaxAmt4(rs.getDouble("TAX_AMT4"));
		model.setTaxCode(rs.getString("tax_code"));
		model.setTaxCode2(rs.getString("tax_code2"));
		model.setTaxCode3(rs.getString("tax_code3"));
		model.setTaxCode4(rs.getString("tax_code4"));
		model.setTaxRate(rs.getDouble("TAXRATE"));
		model.setTaxRate2(rs.getDouble("tax_rate2"));
		model.setTaxRate3(rs.getDouble("tax_rate3"));
		model.setTaxRate4(rs.getDouble("tax_rate4"));
		model.setUnipak(rs.getDouble("unipak"));
		model.setUnit(rs.getString("UNIT"));
		model.setVarovh(rs.getDouble("varovh"));
		
		return model;
	}

}
