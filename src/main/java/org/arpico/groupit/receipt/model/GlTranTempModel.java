package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.GlTranTempModelPK;

@Entity
@Table(name = "gltrantemp")
public class GlTranTempModel {

	private GlTranTempModelPK glTranTempModelPK;

	private Double amount;
	private Double amtfcu;
	private String astCode;
	private String bnkRec;
	private String createBy;
	private Date createDt;
	private String curCod;
	private String dimm1;
	private String dimm2;
	private String dimm3;
	private String dimm4;
	private String dimm5;
	private String drCrTy;
	private Double exCart;
	private Double interId;
	private String jcat1;
	private String jcat2;
	private String jcat3;
	private String jcat4;
	private String jcat5;
	private Double lkrrat;
	private String modiBy;
	private String modiDate;
	private String modiType;
	private String period;
	private String postDate;
	private String reaCode;
	private String refTxt;
	private String remark;
	private String tranDt;
	private String unofms;
	private Double unitQty;

	@EmbeddedId
	public GlTranTempModelPK getGlTranTempModelPK() {
		return glTranTempModelPK;
	}

	public void setGlTranTempModelPK(GlTranTempModelPK glTranTempModelPK) {
		this.glTranTempModelPK = glTranTempModelPK;
	}

	@Column(name = "AMOUNT")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "AMTFCU")
	public Double getAmtfcu() {
		return amtfcu;
	}

	public void setAmtfcu(Double amtfcu) {
		this.amtfcu = amtfcu;
	}

	@Column(name = "astcod")
	public String getAstCode() {
		return astCode;
	}

	public void setAstCode(String astCode) {
		this.astCode = astCode;
	}

	@Column(name = "bnkrec")
	public String getBnkRec() {
		return bnkRec;
	}

	public void setBnkRec(String bnkRec) {
		this.bnkRec = bnkRec;
	}

	@Column(name = "CREABY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREADT")
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Column(name = "CURCOD")
	public String getCurCod() {
		return curCod;
	}

	public void setCurCod(String curCod) {
		this.curCod = curCod;
	}

	@Column(name = "DIMM01")
	public String getDimm1() {
		return dimm1;
	}

	public void setDimm1(String dimm1) {
		this.dimm1 = dimm1;
	}

	@Column(name = "DIMM02")
	public String getDimm2() {
		return dimm2;
	}

	public void setDimm2(String dimm2) {
		this.dimm2 = dimm2;
	}

	@Column(name = "DIMM03")
	public String getDimm3() {
		return dimm3;
	}

	public void setDimm3(String dimm3) {
		this.dimm3 = dimm3;
	}

	@Column(name = "DIMM04")
	public String getDimm4() {
		return dimm4;
	}

	public void setDimm4(String dimm4) {
		this.dimm4 = dimm4;
	}

	@Column(name = "DIMM05")
	public String getDimm5() {
		return dimm5;
	}

	public void setDimm5(String dimm5) {
		this.dimm5 = dimm5;
	}

	@Column(name = "DRCRTY")
	public String getDrCrTy() {
		return drCrTy;
	}

	public void setDrCrTy(String drCrTy) {
		this.drCrTy = drCrTy;
	}

	@Column(name = "EXCRAT")
	public Double getExCart() {
		return exCart;
	}

	public void setExCart(Double exCart) {
		this.exCart = exCart;
	}

	@Column(name = "INTERID")
	public Double getInterId() {
		return interId;
	}

	public void setInterId(Double interId) {
		this.interId = interId;
	}

	@Column(name = "JCAT01")
	public String getJcat1() {
		return jcat1;
	}

	public void setJcat1(String jcat1) {
		this.jcat1 = jcat1;
	}

	@Column(name = "JCAT02")
	public String getJcat2() {
		return jcat2;
	}

	public void setJcat2(String jcat2) {
		this.jcat2 = jcat2;
	}

	@Column(name = "JCAT03")
	public String getJcat3() {
		return jcat3;
	}

	public void setJcat3(String jcat3) {
		this.jcat3 = jcat3;
	}

	@Column(name = "JCAT04")
	public String getJcat4() {
		return jcat4;
	}

	public void setJcat4(String jcat4) {
		this.jcat4 = jcat4;
	}

	@Column(name = "JCAT05")
	public String getJcat5() {
		return jcat5;
	}

	public void setJcat5(String jcat5) {
		this.jcat5 = jcat5;
	}

	@Column(name = "lkrrat")
	public Double getLkrrat() {
		return lkrrat;
	}

	public void setLkrrat(Double lkrrat) {
		this.lkrrat = lkrrat;
	}

	@Column(name = "MODIBY")
	public String getModiBy() {
		return modiBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	@Column(name = "MODIDT")
	public String getModiDate() {
		return modiDate;
	}

	public void setModiDate(String modiDate) {
		this.modiDate = modiDate;
	}

	@Column(name = "MODTYP")
	public String getModiType() {
		return modiType;
	}

	public void setModiType(String modiType) {
		this.modiType = modiType;
	}

	@Column(name = "period")
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Column(name = "postdt")
	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Column(name = "reacod")
	public String getReaCode() {
		return reaCode;
	}

	public void setReaCode(String reaCode) {
		this.reaCode = reaCode;
	}

	@Column(name = "reftxt")
	public String getRefTxt() {
		return refTxt;
	}

	public void setRefTxt(String refTxt) {
		this.refTxt = refTxt;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TRANDT")
	public String getTranDt() {
		return tranDt;
	}

	public void setTranDt(String tranDt) {
		this.tranDt = tranDt;
	}

	@Column(name = "UNOFMS")
	public String getUnofms() {
		return unofms;
	}

	public void setUnofms(String unofms) {
		this.unofms = unofms;
	}

	@Column(name = "UNTQTY")
	public Double getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Double unitQty) {
		this.unitQty = unitQty;
	}

}
