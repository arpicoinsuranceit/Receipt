package org.arpico.groupit.receipt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.RmsDocTxndModelPK;

@Entity
@Table(name = "rms_doc_txnd")
public class RmsDocTxndModel implements Serializable {

	private RmsDocTxndModelPK rmsDocTxndModelPK;

	private Double amtfcu;

	private Double avgpri;

	private String createBy;

	private Date creDate;

	private String descri;

	private String dimm04;

	private String dimm05;

	private String dimm06;

	private String dimm07;

	private String dimm08;

	private String dimm09;

	private String dimm10;

	private Double discReimberse;

	private Double disrate;

	private String dstat;

	private Double enecst;

	private Double fixovh;

	private String glgrup;

	private Double interid;

	private Double issuedQty;

	private String itemCode;

	private String itemGroup;

	private Integer jobHid;

	private String jobNo;

	private Integer jobSeq;

	private String jobTyp;

	private String labCst;

	private Double lotnum;

	private Double matcst;

	private String mod_by;

	private String mod_date;

	private String pluCode;

	private Integer posNo;

	private String prcflg;

	private Double price;

	private Double profiteRate;

	private Double qty;

	private String reaCode;

	private String remark;

	private String sourceDocCode;

	private Integer sourceDocNo;

	private Double stdcst;

	private String stockLoc;

	private String sysupload;

	private Double taxAmt1;

	private Double taxAmt2;

	private Double taxAmt3;

	private Double taxAmt4;

	private String taxCode;

	private String taxCode2;

	private String taxCode3;

	private String taxCode4;

	private Double taxRate2;

	private Double taxRate3;

	private Double taxRate4;

	private Double taxRate;

	private Double unipak;

	private String unit;

	private Double varovh;

	@EmbeddedId
	public RmsDocTxndModelPK getRmsDocTxndModelPK() {
		return rmsDocTxndModelPK;
	}

	public void setRmsDocTxndModelPK(RmsDocTxndModelPK rmsDocTxndModelPK) {
		this.rmsDocTxndModelPK = rmsDocTxndModelPK;
	}

	@Column(name = "amtfcu")
	public Double getAmtfcu() {
		return amtfcu;
	}

	public void setAmtfcu(Double amtfcu) {
		this.amtfcu = amtfcu;
	}

	@Column(name = "AVG_PRI")
	public Double getAvgpri() {
		return avgpri;
	}

	public void setAvgpri(Double avgpri) {
		this.avgpri = avgpri;
	}

	@Column(name = "CRE_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CRE_DATE")
	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	@Column(name = "descri")
	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	@Column(name = "dimm04")
	public String getDimm04() {
		return dimm04;
	}

	public void setDimm04(String dimm04) {
		this.dimm04 = dimm04;
	}

	@Column(name = "dimm05")
	public String getDimm05() {
		return dimm05;
	}

	public void setDimm05(String dimm05) {
		this.dimm05 = dimm05;
	}

	@Column(name = "dimm06")
	public String getDimm06() {
		return dimm06;
	}

	public void setDimm06(String dimm06) {
		this.dimm06 = dimm06;
	}

	@Column(name = "dimm07")
	public String getDimm07() {
		return dimm07;
	}

	public void setDimm07(String dimm07) {
		this.dimm07 = dimm07;
	}

	@Column(name = "dimm08")
	public String getDimm08() {
		return dimm08;
	}

	public void setDimm08(String dimm08) {
		this.dimm08 = dimm08;
	}

	@Column(name = "dimm09")
	public String getDimm09() {
		return dimm09;
	}

	public void setDimm09(String dimm09) {
		this.dimm09 = dimm09;
	}

	@Column(name = "dimm10")
	public String getDimm10() {
		return dimm10;
	}

	public void setDimm10(String dimm10) {
		this.dimm10 = dimm10;
	}

	@Column(name = "disc_reimberse")
	public Double getDiscReimberse() {
		return discReimberse;
	}

	public void setDiscReimberse(Double discReimberse) {
		this.discReimberse = discReimberse;
	}

	@Column(name = "DISRATE")
	public Double getDisrate() {
		return disrate;
	}

	public void setDisrate(Double disrate) {
		this.disrate = disrate;
	}

	@Column(name = "DSTAT")
	public String getDstat() {
		return dstat;
	}

	public void setDstat(String dstat) {
		this.dstat = dstat;
	}

	@Column(name = "enecst")
	public Double getEnecst() {
		return enecst;
	}

	public void setEnecst(Double enecst) {
		this.enecst = enecst;
	}

	@Column(name = "fixovh")
	public Double getFixovh() {
		return fixovh;
	}

	public void setFixovh(Double fixovh) {
		this.fixovh = fixovh;
	}

	@Column(name = "glgrup")
	public String getGlgrup() {
		return glgrup;
	}

	public void setGlgrup(String glgrup) {
		this.glgrup = glgrup;
	}

	@Column(name = "interid")
	public Double getInterid() {
		return interid;
	}

	public void setInterid(Double interid) {
		this.interid = interid;
	}

	@Column(name = "ISSUED_QTY")
	public Double getIssuedQty() {
		return issuedQty;
	}

	public void setIssuedQty(Double issuedQty) {
		this.issuedQty = issuedQty;
	}

	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "itmgrp")
	public String getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}

	@Column(name = "job_hid")
	public Integer getJobHid() {
		return jobHid;
	}

	public void setJobHid(Integer jobHid) {
		this.jobHid = jobHid;
	}

	@Column(name = "job_no")
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	@Column(name = "jobseq")
	public Integer getJobSeq() {
		return jobSeq;
	}

	public void setJobSeq(Integer jobSeq) {
		this.jobSeq = jobSeq;
	}

	@Column(name = "jobtyp")
	public String getJobTyp() {
		return jobTyp;
	}

	public void setJobTyp(String jobTyp) {
		this.jobTyp = jobTyp;
	}

	@Column(name = "labcst")
	public String getLabCst() {
		return labCst;
	}

	public void setLabCst(String labCst) {
		this.labCst = labCst;
	}

	@Column(name = "lotnum")
	public Double getLotnum() {
		return lotnum;
	}

	public void setLotnum(Double lotnum) {
		this.lotnum = lotnum;
	}

	@Column(name = "matcst")
	public Double getMatcst() {
		return matcst;
	}

	public void setMatcst(Double matcst) {
		this.matcst = matcst;
	}

	@Column(name = "MOD_BY")
	public String getMod_by() {
		return mod_by;
	}

	public void setMod_by(String mod_by) {
		this.mod_by = mod_by;
	}

	@Column(name = "MOD_DATE")
	public String getMod_date() {
		return mod_date;
	}

	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}

	@Column(name = "PLU_CODE")
	public String getPluCode() {
		return pluCode;
	}

	public void setPluCode(String pluCode) {
		this.pluCode = pluCode;
	}

	@Column(name = "POS_NO")
	public Integer getPosNo() {
		return posNo;
	}

	public void setPosNo(Integer posNo) {
		this.posNo = posNo;
	}

	@Column(name = "prcflg")
	public String getPrcflg() {
		return prcflg;
	}

	public void setPrcflg(String prcflg) {
		this.prcflg = prcflg;
	}

	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "PROFITRATE")
	public Double getProfiteRate() {
		return profiteRate;
	}

	public void setProfiteRate(Double profiteRate) {
		this.profiteRate = profiteRate;
	}

	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	@Column(name = "REA_CODE")
	public String getReaCode() {
		return reaCode;
	}

	public void setReaCode(String reaCode) {
		this.reaCode = reaCode;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "SOURCE_DOC_CODE")
	public String getSourceDocCode() {
		return sourceDocCode;
	}

	public void setSourceDocCode(String sourceDocCode) {
		this.sourceDocCode = sourceDocCode;
	}

	@Column(name = "SOURCE_DOC_NO")
	public Integer getSourceDocNo() {
		return sourceDocNo;
	}

	public void setSourceDocNo(Integer sourceDocNo) {
		this.sourceDocNo = sourceDocNo;
	}

	@Column(name = "stdcst")
	public Double getStdcst() {
		return stdcst;
	}

	public void setStdcst(Double stdcst) {
		this.stdcst = stdcst;
	}

	@Column(name = "STOCK_LOC")
	public String getStockLoc() {
		return stockLoc;
	}

	public void setStockLoc(String stockLoc) {
		this.stockLoc = stockLoc;
	}

	@Column(name = "sysupload")
	public String getSysupload() {
		return sysupload;
	}

	public void setSysupload(String sysupload) {
		this.sysupload = sysupload;
	}

	@Column(name = "TAX_AMT1")
	public Double getTaxAmt1() {
		return taxAmt1;
	}

	public void setTaxAmt1(Double taxAmt1) {
		this.taxAmt1 = taxAmt1;
	}

	@Column(name = "TAX_AMT2")
	public Double getTaxAmt2() {
		return taxAmt2;
	}

	public void setTaxAmt2(Double taxAmt2) {
		this.taxAmt2 = taxAmt2;
	}

	@Column(name = "TAX_AMT3")
	public Double getTaxAmt3() {
		return taxAmt3;
	}

	public void setTaxAmt3(Double taxAmt3) {
		this.taxAmt3 = taxAmt3;
	}

	@Column(name = "TAX_AMT4")
	public Double getTaxAmt4() {
		return taxAmt4;
	}

	public void setTaxAmt4(Double taxAmt4) {
		this.taxAmt4 = taxAmt4;
	}

	@Column(name = "tax_code")
	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Column(name = "tax_code2")
	public String getTaxCode2() {
		return taxCode2;
	}

	public void setTaxCode2(String taxCode2) {
		this.taxCode2 = taxCode2;
	}

	@Column(name = "tax_code3")
	public String getTaxCode3() {
		return taxCode3;
	}

	public void setTaxCode3(String taxCode3) {
		this.taxCode3 = taxCode3;
	}

	@Column(name = "tax_code4")
	public String getTaxCode4() {
		return taxCode4;
	}

	public void setTaxCode4(String taxCode4) {
		this.taxCode4 = taxCode4;
	}

	@Column(name = "tax_rate2")
	public Double getTaxRate2() {
		return taxRate2;
	}

	public void setTaxRate2(Double taxRate2) {
		this.taxRate2 = taxRate2;
	}

	@Column(name = "tax_rate3")
	public Double getTaxRate3() {
		return taxRate3;
	}

	public void setTaxRate3(Double taxRate3) {
		this.taxRate3 = taxRate3;
	}

	@Column(name = "tax_rate4")
	public Double getTaxRate4() {
		return taxRate4;
	}

	public void setTaxRate4(Double taxRate4) {
		this.taxRate4 = taxRate4;
	}

	@Column(name = "TAXRATE")
	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "varovh")
	public Double getVarovh() {
		return varovh;
	}

	public void setVarovh(Double varovh) {
		this.varovh = varovh;
	}

	@Column(name = "unipak")
	public Double getUnipak() {
		return unipak;
	}

	public void setUnipak(Double unipak) {
		this.unipak = unipak;
	}

	@Override
	public String toString() {
		return "RmsDocTxndModel [rmsDocTxndModelPK=" + rmsDocTxndModelPK + ", amtfcu=" + amtfcu + ", avgpri=" + avgpri
				+ ", createBy=" + createBy + ", creDate=" + creDate + ", descri=" + descri + ", dimm04=" + dimm04
				+ ", dimm05=" + dimm05 + ", dimm06=" + dimm06 + ", dimm07=" + dimm07 + ", dimm08=" + dimm08
				+ ", dimm09=" + dimm09 + ", dimm10=" + dimm10 + ", discReimberse=" + discReimberse + ", disrate="
				+ disrate + ", dstat=" + dstat + ", enecst=" + enecst + ", fixovh=" + fixovh + ", glgrup=" + glgrup
				+ ", interid=" + interid + ", issuedQty=" + issuedQty + ", itemCode=" + itemCode + ", itemGroup="
				+ itemGroup + ", jobHid=" + jobHid + ", jobNo=" + jobNo + ", jobSeq=" + jobSeq + ", jobTyp=" + jobTyp
				+ ", labCst=" + labCst + ", lotnum=" + lotnum + ", matcst=" + matcst + ", mod_by=" + mod_by
				+ ", mod_date=" + mod_date + ", pluCode=" + pluCode + ", posNo=" + posNo + ", prcflg=" + prcflg
				+ ", price=" + price + ", profiteRate=" + profiteRate + ", qty=" + qty + ", reaCode=" + reaCode
				+ ", remark=" + remark + ", sourceDocCode=" + sourceDocCode + ", sourceDocNo=" + sourceDocNo
				+ ", stdcst=" + stdcst + ", stockLoc=" + stockLoc + ", sysupload=" + sysupload + ", taxAmt1=" + taxAmt1
				+ ", taxAmt2=" + taxAmt2 + ", taxAmt3=" + taxAmt3 + ", taxAmt4=" + taxAmt4 + ", taxCode=" + taxCode
				+ ", taxCode2=" + taxCode2 + ", taxCode3=" + taxCode3 + ", taxCode4=" + taxCode4 + ", taxRate2="
				+ taxRate2 + ", taxRate3=" + taxRate3 + ", taxRate4=" + taxRate4 + ", taxRate=" + taxRate + ", unipak="
				+ unipak + ", unit=" + unit + ", varovh=" + varovh + "]";
	}

}
