package org.arpico.groupit.receipt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.RmsRecmModelPK;

@Entity
@Table(name = "rms_recm")
public class RmsRecmModel implements Serializable {

	private RmsRecmModelPK rmsRecmModelPK;
	private Double amtfcu;
	private Double batcno;
	private String battyp;
	private String creBy;
	private Date creDate;
	private String cscode;
	private String currCode;
	private String custSup;
	private String excrat;
	private String glgrup;
	private String glupdt;
	private String jobNo;
	private String modBy;
	private String modDate;
	private String mstat;
	private String oldRemark;
	private Double pdBal;
	private String pdChq;
	private Double recAmt;
	private Double recBal;
	private String remark;
	private String repId;
	private String repManFlg;
	private String sysupload;
	private Date txnDate;

	@EmbeddedId
	public RmsRecmModelPK getRmsRecmModelPK() {
		return rmsRecmModelPK;
	}

	public void setRmsRecmModelPK(RmsRecmModelPK rmsRecmModelPK) {
		this.rmsRecmModelPK = rmsRecmModelPK;
	}

	@Column(name = "AMTFCU")
	public Double getAmtfcu() {
		return amtfcu;
	}

	public void setAmtfcu(Double amtfcu) {
		this.amtfcu = amtfcu;
	}

	@Column(name = "BATCNO")
	public Double getBatcno() {
		return batcno;
	}

	public void setBatcno(Double batcno) {
		this.batcno = batcno;
	}

	@Column(name = "BATTYP")
	public String getBattyp() {
		return battyp;
	}

	public void setBattyp(String battyp) {
		this.battyp = battyp;
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

	@Column(name = "CS_CODE")
	public String getCscode() {
		return cscode;
	}

	public void setCscode(String cscode) {
		this.cscode = cscode;
	}

	@Column(name = "CURR_CODE")
	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	@Column(name = "CUST_SUP")
	public String getCustSup() {
		return custSup;
	}

	public void setCustSup(String custSup) {
		this.custSup = custSup;
	}

	@Column(name = "EXCRAT")
	public String getExcrat() {
		return excrat;
	}

	public void setExcrat(String excrat) {
		this.excrat = excrat;
	}

	@Column(name = "glgrup")
	public String getGlgrup() {
		return glgrup;
	}

	public void setGlgrup(String glgrup) {
		this.glgrup = glgrup;
	}

	@Column(name = "GLUPDT")
	public String getGlupdt() {
		return glupdt;
	}

	public void setGlupdt(String glupdt) {
		this.glupdt = glupdt;
	}

	@Column(name = "JOB_NO")
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
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

	@Column(name = "MSTAT")
	public String getMstat() {
		return mstat;
	}

	public void setMstat(String mstat) {
		this.mstat = mstat;
	}

	@Column(name = "old_remark")
	public String getOldRemark() {
		return oldRemark;
	}

	public void setOldRemark(String oldRemark) {
		this.oldRemark = oldRemark;
	}

	@Column(name = "PD_BAL")
	public Double getPdBal() {
		return pdBal;
	}

	public void setPdBal(Double pdBal) {
		this.pdBal = pdBal;
	}

	@Column(name = "PD_CHQ")
	public String getPdChq() {
		return pdChq;
	}

	public void setPdChq(String pdChq) {
		this.pdChq = pdChq;
	}

	@Column(name = "REC_AMT")
	public Double getRecAmt() {
		return recAmt;
	}

	public void setRecAmt(Double recAmt) {
		this.recAmt = recAmt;
	}

	@Column(name = "REC_BAL")
	public Double getRecBal() {
		return recBal;
	}

	public void setRecBal(Double recBal) {
		this.recBal = recBal;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REP_ID")
	public String getRepId() {
		return repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	@Column(name = "REP_MAN_FLG")
	public String getRepManFlg() {
		return repManFlg;
	}

	public void setRepManFlg(String repManFlg) {
		this.repManFlg = repManFlg;
	}

	@Column(name = "sysupload")
	public String getSysupload() {
		return sysupload;
	}

	public void setSysupload(String sysupload) {
		this.sysupload = sysupload;
	}

	@Column(name = "TXN_DATE")
	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	@Override
	public String toString() {
		return "RmsRecmModel [rmsRecmModelPK=" + rmsRecmModelPK + ", amtfcu=" + amtfcu + ", batcno=" + batcno
				+ ", battyp=" + battyp + ", creBy=" + creBy + ", creDate=" + creDate + ", cscode=" + cscode
				+ ", currCode=" + currCode + ", custSup=" + custSup + ", excrat=" + excrat + ", glgrup=" + glgrup
				+ ", glupdt=" + glupdt + ", jobNo=" + jobNo + ", modBy=" + modBy + ", modDate=" + modDate + ", mstat="
				+ mstat + ", oldRemark=" + oldRemark + ", pdBal=" + pdBal + ", pdChq=" + pdChq + ", recAmt=" + recAmt
				+ ", recBal=" + recBal + ", remark=" + remark + ", repId=" + repId + ", repManFlg=" + repManFlg
				+ ", sysupload=" + sysupload + ", txnDate=" + txnDate + "]";
	}

}
