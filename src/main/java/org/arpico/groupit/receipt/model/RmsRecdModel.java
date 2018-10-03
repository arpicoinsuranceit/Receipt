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
	private Double amt;
	private Double amtfcu;
	private String bankBra;
	private String bankCode;
	private Date bnkDt;
	private String crdTyp;
	private String creBy;
	private Date creDate;
	private String dimm04;
	private String dimm05;
	private String modBy;
	private String modDate;
	private String oldBkDt;
	private String payMode;
	private Date pcChqDt;
	private String remarks;
	private String voucherNo;
	private Integer vouSeq;

	@EmbeddedId
	public RmsRecdModelPK getRmsRecdModelPK() {
		return rmsRecdModelPK;
	}

	public void setRmsRecdModelPK(RmsRecdModelPK rmsRecdModelPK) {
		this.rmsRecdModelPK = rmsRecdModelPK;
	}

	@Column(name = "AMT")
	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	@Column(name = "AMTFCU")
	public Double getAmtfcu() {
		return amtfcu;
	}

	public void setAmtfcu(Double amtfcu) {
		this.amtfcu = amtfcu;
	}

	@Column(name = "BANK_BRA")
	public String getBankBra() {
		return bankBra;
	}

	public void setBankBra(String bankBra) {
		this.bankBra = bankBra;
	}

	@Column(name = "BANK_CODE")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "BNK_DT")
	public Date getBnkDt() {
		return bnkDt;
	}

	public void setBnkDt(Date bnkDt) {
		this.bnkDt = bnkDt;
	}

	@Column(name = "CRD_TYP")
	public String getCrdTyp() {
		return crdTyp;
	}

	public void setCrdTyp(String crdTyp) {
		this.crdTyp = crdTyp;
	}

	@Column(name = "CRE_BY")
	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	@Column(name = "CRE_DATE")
	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
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

	@Column(name = "MOD_BY")
	public String getModBy() {
		return modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	@Column(name = "MOD_DATE")
	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	@Column(name = "old_bkdt")
	public String getOldBkDt() {
		return oldBkDt;
	}

	public void setOldBkDt(String oldBkDt) {
		this.oldBkDt = oldBkDt;
	}

	@Column(name = "PAY_MODE")
	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Column(name = "PD_CHQDT")
	public Date getPcChqDt() {
		return pcChqDt;
	}

	public void setPcChqDt(Date pcChqDt) {
		this.pcChqDt = pcChqDt;
	}

	@Column(name = "REMERKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "VOUCHER_NO")
	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Column(name = "VOUSEQ")
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
