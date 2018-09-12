package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.RmsRecdModelPK;

@Entity
@Table(name = "rms_recd")
public class RmsRecdModel {
	
	private RmsRecdModelPK rmsRecdModelPK;
	
	@Column(name = "AMT")
	private Double amt;
	
	@Column(name = "AMTFCU")
	private Double amtfcu;
	
	@Column(name = "BANK_BRA")
	private String bankBra;
	
	@Column(name = "BANK_CODE")
	private String bankCode;
	
	@Column(name = "BNK_DT")
	private Date bnkDt;
	
	@Column(name = "CRD_TYP")
	private String crdTyp;
	
	@Column(name = "CRE_BY")
	private String creBy;
	
	@Column(name = "CRE_DATE")
	private Date creDate;
	
	@Column(name = "dimm04")
	private String dimm04;
	
	@Column(name = "dimm05")
	private String dimm05;
	
	@Column(name = "MOD_BY")
	private String modBy;
	
	@Column(name = "MOD_DATE")
	private Date modDate;
	
	@Column(name = "old_bkdt")
	private Date oldBkDt;
	
	@Column(name = "PAY_MODE")
	private String payMode;
	
	@Column(name = "PD_CHQDT")
	private Date pcChqDt;

	@Column(name = "REMERKS")
	private String remarks;
	
	@Column(name = "VOUCHER_NO")
	private String voucherNo;
	
	@Column(name = "VOUSEQ")
	private Integer vouSeq;
	
	

	@EmbeddedId
	public RmsRecdModelPK getRmsRecdModelPK() {
		return rmsRecdModelPK;
	}

	public void setRmsRecdModelPK(RmsRecdModelPK rmsRecdModelPK) {
		this.rmsRecdModelPK = rmsRecdModelPK;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public Double getAmtfcu() {
		return amtfcu;
	}

	public void setAmtfcu(Double amtfcu) {
		this.amtfcu = amtfcu;
	}

	public String getBankBra() {
		return bankBra;
	}

	public void setBankBra(String bankBra) {
		this.bankBra = bankBra;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Date getBnkDt() {
		return bnkDt;
	}

	public void setBnkDt(Date bnkDt) {
		this.bnkDt = bnkDt;
	}

	public String getCrdTyp() {
		return crdTyp;
	}

	public void setCrdTyp(String crdTyp) {
		this.crdTyp = crdTyp;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getDimm04() {
		return dimm04;
	}

	public void setDimm04(String dimm04) {
		this.dimm04 = dimm04;
	}

	public String getDimm05() {
		return dimm05;
	}

	public void setDimm05(String dimm05) {
		this.dimm05 = dimm05;
	}

	public String getModBy() {
		return modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public Date getOldBkDt() {
		return oldBkDt;
	}

	public void setOldBkDt(Date oldBkDt) {
		this.oldBkDt = oldBkDt;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Date getPcChqDt() {
		return pcChqDt;
	}

	public void setPcChqDt(Date pcChqDt) {
		this.pcChqDt = pcChqDt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Integer getVouSeq() {
		return vouSeq;
	}

	public void setVouSeq(Integer vouSeq) {
		this.vouSeq = vouSeq;
	}

	@Override
	public String toString() {
		return "RmsRecdModel [rmsRecdModelPK=" + rmsRecdModelPK + ", amt=" + amt + ", amtfcu=" + amtfcu + ", bankBra="
				+ bankBra + ", bankCode=" + bankCode + ", bnkDt=" + bnkDt + ", crdTyp=" + crdTyp + ", creBy=" + creBy
				+ ", creDate=" + creDate + ", dimm04=" + dimm04 + ", dimm05=" + dimm05 + ", modBy=" + modBy
				+ ", modDate=" + modDate + ", oldBkDt=" + oldBkDt + ", payMode=" + payMode + ", pcChqDt=" + pcChqDt
				+ ", remarks=" + remarks + ", voucherNo=" + voucherNo + ", vouSeq=" + vouSeq + "]";
	}

	
}
