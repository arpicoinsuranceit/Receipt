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

	@Column(name = "AMTFCU")
	private Double amtfcu;

	@Column(name = "BATCNO")
	private Double batcno;

	@Column(name = "BATTYP")
	private String battyp;

	@Column(name = "CRE_BY")
	private String creBy;

	@Column(name = "CRE_DATE")
	private Date creDate;

	@Column(name = "CS_CODE")
	private String cscode;

	@Column(name = "CURR_CODE")
	private String currCode;

	@Column(name = "CUST_SUP")
	private String custSup;

	@Column(name = "EXCRAT")
	private String excrat;

	@Column(name = "glgrup")
	private String glgrup;

	@Column(name = "GLUPDT")
	private String glupdt;

	@Column(name = "JOB_NO")
	private String jobNo;

	@Column(name = "MOD_BY")
	private String modBy;

	@Column(name = "MOD_DATE")
	private Date modDate;

	@Column(name = "MSTAT")
	private String mstat;

	@Column(name = "old_remark")
	private String oldRemark;

	@Column(name = "PD_BAL")
	private Double pdBal;

	@Column(name = "PD_CHQ")
	private String pdChq;

	@Column(name = "REC_AMT")
	private Double recAmt;

	@Column(name = "REC_BAL")
	private Double recBal;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "REP_ID")
	private String repId;

	@Column(name = "REP_MAN_FLG")
	private String repManFlg;

	@Column(name = "sysupload")
	private String sysupload;

	@Column(name = "TXN_DATE")
	private Date txnDate;

	@EmbeddedId
	public RmsRecmModelPK getRmsRecmModelPK() {
		return rmsRecmModelPK;
	}

	public void setRmsRecmModelPK(RmsRecmModelPK rmsRecmModelPK) {
		this.rmsRecmModelPK = rmsRecmModelPK;
	}

	public Double getAmtfcu() {
		return amtfcu;
	}

	public void setAmtfcu(Double amtfcu) {
		this.amtfcu = amtfcu;
	}

	public Double getBatcno() {
		return batcno;
	}

	public void setBatcno(Double batcno) {
		this.batcno = batcno;
	}

	public String getBattyp() {
		return battyp;
	}

	public void setBattyp(String battyp) {
		this.battyp = battyp;
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

	public String getCscode() {
		return cscode;
	}

	public void setCscode(String cscode) {
		this.cscode = cscode;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getCustSup() {
		return custSup;
	}

	public void setCustSup(String custSup) {
		this.custSup = custSup;
	}

	public String getExcrat() {
		return excrat;
	}

	public void setExcrat(String excrat) {
		this.excrat = excrat;
	}

	public String getGlgrup() {
		return glgrup;
	}

	public void setGlgrup(String glgrup) {
		this.glgrup = glgrup;
	}

	public String getGlupdt() {
		return glupdt;
	}

	public void setGlupdt(String glupdt) {
		this.glupdt = glupdt;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
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

	public String getMstat() {
		return mstat;
	}

	public void setMstat(String mstat) {
		this.mstat = mstat;
	}

	public String getOldRemark() {
		return oldRemark;
	}

	public void setOldRemark(String oldRemark) {
		this.oldRemark = oldRemark;
	}

	public Double getPdBal() {
		return pdBal;
	}

	public void setPdBal(Double pdBal) {
		this.pdBal = pdBal;
	}

	public String getPdChq() {
		return pdChq;
	}

	public void setPdChq(String pdChq) {
		this.pdChq = pdChq;
	}

	public Double getRecAmt() {
		return recAmt;
	}

	public void setRecAmt(Double recAmt) {
		this.recAmt = recAmt;
	}

	public Double getRecBal() {
		return recBal;
	}

	public void setRecBal(Double recBal) {
		this.recBal = recBal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRepId() {
		return repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getRepManFlg() {
		return repManFlg;
	}

	public void setRepManFlg(String repManFlg) {
		this.repManFlg = repManFlg;
	}

	public String getSysupload() {
		return sysupload;
	}

	public void setSysupload(String sysupload) {
		this.sysupload = sysupload;
	}

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
