package org.arpico.groupit.receipt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.RmsDocTxnmModelPK;

@Entity
@Table(name = "rms_doc_txnm")
public class RmsDocTxnmModel implements Serializable {

	private RmsDocTxnmModelPK rmsDocTxnmModelPK;

	private Double amtfcu;

	private Double batcno;

	private String battyp;

	private String confirmd;

	private String cnfuser;

	private String costcent;

	private String creBy;

	private Date creDate;

	private String currCode;

	private String custSupCode;

	private String custSupF;

	private String deliDate;

	private Integer detlineSeq;

	private String downloaded;

	private Double excrat;

	private String formInvloc;

	private String fromLoc;

	private String glgrup;

	private String glupdt;

	private Double invAmt;

	private String isuBy;

	private String jobNo;

	private String locser;

	private String modBy;

	private String modDate;

	private String mstat;

	private String passBy;

	private String recBy;

	private String ref1;

	private String ref2;

	private String ref3;

	private String ref4;

	private String ref5;

	private String remarks;

	private String repId;

	private String reqBy;

	private Double setOffAmt;

	private Double setOffAmtPd;

	private Double taxAmt1;

	private Double taxAmt2;

	private String toInvLoc;

	private String toLoc;

	private Double tradis;

	private Date txnDate;

	private String vourai;

	@EmbeddedId
	public RmsDocTxnmModelPK getRmsDocTxnmModelPK() {
		return rmsDocTxnmModelPK;
	}

	public void setRmsDocTxnmModelPK(RmsDocTxnmModelPK rmsDocTxnmModelPK) {
		this.rmsDocTxnmModelPK = rmsDocTxnmModelPK;
	}

	@Column(name = "amtfcu")
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

	@Column(name = "CONFIRMD")
	public String getConfirmd() {
		return confirmd;
	}

	public void setConfirmd(String confirmd) {
		this.confirmd = confirmd;
	}

	@Column(name = "CONFUSER")
	public String getCnfuser() {
		return cnfuser;
	}

	public void setCnfuser(String cnfuser) {
		this.cnfuser = cnfuser;
	}

	@Column(name = "COSTCENT")
	public String getCostcent() {
		return costcent;
	}

	public void setCostcent(String costcent) {
		this.costcent = costcent;
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

	@Column(name = "CURR_CODE")
	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	@Column(name = "CUST_SUP_CODE")
	public String getCustSupCode() {
		return custSupCode;
	}

	public void setCustSupCode(String custSupCode) {
		this.custSupCode = custSupCode;
	}

	@Column(name = "CUST_SUP_F")
	public String getCustSupF() {
		return custSupF;
	}

	public void setCustSupF(String custSupF) {
		this.custSupF = custSupF;
	}

	@Column(name = "DELIDATE")
	public String getDeliDate() {
		return deliDate;
	}

	public void setDeliDate(String deliDate) {
		this.deliDate = deliDate;
	}

	@Column(name = "DET_LINE_SEQ")
	public Integer getDetlineSeq() {
		return detlineSeq;
	}

	public void setDetlineSeq(Integer detlineSeq) {
		this.detlineSeq = detlineSeq;
	}

	@Column(name = "DOWNLOADED")
	public String getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(String downloaded) {
		this.downloaded = downloaded;
	}

	@Column(name = "EXCRAT")
	public Double getExcrat() {
		return excrat;
	}

	public void setExcrat(Double excrat) {
		this.excrat = excrat;
	}

	@Column(name = "FROM_INVLOC")
	public String getFormInvloc() {
		return formInvloc;
	}

	public void setFormInvloc(String formInvloc) {
		this.formInvloc = formInvloc;
	}

	@Column(name = "FROM_LOC")
	public String getFromLoc() {
		return fromLoc;
	}

	public void setFromLoc(String fromLoc) {
		this.fromLoc = fromLoc;
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

	@Column(name = "INV_AMT")
	public Double getInvAmt() {
		return invAmt;
	}

	public void setInvAmt(Double invAmt) {
		this.invAmt = invAmt;
	}

	@Column(name = "ISU_BY")
	public String getIsuBy() {
		return isuBy;
	}

	public void setIsuBy(String isuBy) {
		this.isuBy = isuBy;
	}

	@Column(name = "JOB_NO")
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	@Column(name = "locser")
	public String getLocser() {
		return locser;
	}

	public void setLocser(String locser) {
		this.locser = locser;
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

	@Column(name = "PASS_BY")
	public String getPassBy() {
		return passBy;
	}

	public void setPassBy(String passBy) {
		this.passBy = passBy;
	}

	@Column(name = "REC_BY")
	public String getRecBy() {
		return recBy;
	}

	public void setRecBy(String recBy) {
		this.recBy = recBy;
	}

	@Column(name = "REF1")
	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	@Column(name = "REF2")
	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	@Column(name = "REF3")
	public String getRef3() {
		return ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	@Column(name = "REF4")
	public String getRef4() {
		return ref4;
	}

	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}

	@Column(name = "REF5")
	public String getRef5() {
		return ref5;
	}

	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "REP_ID")
	public String getRepId() {
		return repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	@Column(name = "REQ_BY")
	public String getReqBy() {
		return reqBy;
	}

	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}

	@Column(name = "SET_OFF_AMT")
	public Double getSetOffAmt() {
		return setOffAmt;
	}

	public void setSetOffAmt(Double setOffAmt) {
		this.setOffAmt = setOffAmt;
	}

	@Column(name = "SET_OFF_AMT_PD")
	public Double getSetOffAmtPd() {
		return setOffAmtPd;
	}

	public void setSetOffAmtPd(Double setOffAmtPd) {
		this.setOffAmtPd = setOffAmtPd;
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

	@Column(name = "TO_INVLOC")
	public String getToInvLoc() {
		return toInvLoc;
	}

	public void setToInvLoc(String toInvLoc) {
		this.toInvLoc = toInvLoc;
	}

	@Column(name = "TO_LOC")
	public String getToLoc() {
		return toLoc;
	}

	public void setToLoc(String toLoc) {
		this.toLoc = toLoc;
	}

	@Column(name = "TRADIS")
	public Double getTradis() {
		return tradis;
	}

	public void setTradis(Double tradis) {
		this.tradis = tradis;
	}

	@Column(name = "TXN_DATE")
	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	@Column(name = "VOURAI")
	public String getVourai() {
		return vourai;
	}

	public void setVourai(String vourai) {
		this.vourai = vourai;
	}

	@Override
	public String toString() {
		return "RmsDocTxnmModel [rmsDocTxnmModelPK=" + rmsDocTxnmModelPK + ", amtfcu=" + amtfcu + ", batcno=" + batcno
				+ ", battyp=" + battyp + ", confirmd=" + confirmd + ", cnfuser=" + cnfuser + ", costcent=" + costcent
				+ ", creBy=" + creBy + ", creDate=" + creDate + ", currCode=" + currCode + ", custSupCode="
				+ custSupCode + ", custSupF=" + custSupF + ", deliDate=" + deliDate + ", detlineSeq=" + detlineSeq
				+ ", downloaded=" + downloaded + ", excrat=" + excrat + ", formInvloc=" + formInvloc + ", fromLoc="
				+ fromLoc + ", glgrup=" + glgrup + ", glupdt=" + glupdt + ", invAmt=" + invAmt + ", isuBy=" + isuBy
				+ ", jobNo=" + jobNo + ", locser=" + locser + ", modBy=" + modBy + ", modDate=" + modDate + ", mstat="
				+ mstat + ", passBy=" + passBy + ", recBy=" + recBy + ", ref1=" + ref1 + ", ref2=" + ref2 + ", ref3="
				+ ref3 + ", ref4=" + ref4 + ", ref5=" + ref5 + ", remarks=" + remarks + ", repId=" + repId + ", reqBy="
				+ reqBy + ", setOffAmt=" + setOffAmt + ", setOffAmtPd=" + setOffAmtPd + ", taxAmt1=" + taxAmt1
				+ ", taxAmt2=" + taxAmt2 + ", toInvLoc=" + toInvLoc + ", toLoc=" + toLoc + ", tradis=" + tradis
				+ ", txnDate=" + txnDate + ", vourai=" + vourai + "]";
	}

}
