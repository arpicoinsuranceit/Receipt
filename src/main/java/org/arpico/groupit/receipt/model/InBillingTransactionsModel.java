package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.arpico.groupit.receipt.util.AppConstant;

@Entity
@Table(name = "inbillingtransactions")
public class InBillingTransactionsModel {

	private InBillingTransactionsModelPK billingTransactionsModelPK;
	private String refdoc;
	private Integer refnum;
	private String srcdoc;
	private Integer srcnum;
	private Integer pprnum;
	private Integer polnum;
	private Integer cscode;
	private String txntyp;
	private Double amount;
	private Double depost;
	private Integer txnyer;
	private Integer txnmth;
	private Integer insnum;
	private String creaby;
	private Date creadt;
	private Date lockin;
	private Integer prpseq;
	private Double polfee;
	private Double admfee;
	private Double taxamt;
	private Double otham1 = AppConstant.ZERO_FOR_DECIMAL;
	private Double otham2 = AppConstant.ZERO_FOR_DECIMAL;
	private Double otham3 = AppConstant.ZERO_FOR_DECIMAL;
	private Double otham4 = AppConstant.ZERO_FOR_DECIMAL;
	private String chqrel;
	private String paymod;
	private Integer toptrm;
	private Double hrbprm = AppConstant.ZERO_FOR_DECIMAL;
	private Integer paytrm;
	private Integer icpyer;
	private Integer prcyer;
	private Double comper = AppConstant.ZERO_FOR_DECIMAL;
	private Double comiss = AppConstant.ZERO_FOR_DECIMAL;
	private Double grsprm;
	private String prdcod;
	private Integer advcod;
	private String agncls;
	private Integer icpmon;
	private String battyp;
	private Integer batcno;
	private String glintg;
	private Integer txnbno = 1;
	private Date duedat;
	private String unlcod;
	private String brncod;
	private Double oldprm = AppConstant.ZERO_FOR_DECIMAL;
	private String candoc;
	private Integer polyer;

	@EmbeddedId
	public InBillingTransactionsModelPK getBillingTransactionsModelPK() {
		return billingTransactionsModelPK;
	}

	public void setBillingTransactionsModelPK(InBillingTransactionsModelPK billingTransactionsModelPK) {
		this.billingTransactionsModelPK = billingTransactionsModelPK;
	}

	public String getRefdoc() {
		return refdoc;
	}

	public void setRefdoc(String refdoc) {
		this.refdoc = refdoc;
	}

	public Integer getRefnum() {
		return refnum;
	}

	public void setRefnum(Integer refnum) {
		this.refnum = refnum;
	}

	public String getSrcdoc() {
		return srcdoc;
	}

	public void setSrcdoc(String srcdoc) {
		this.srcdoc = srcdoc;
	}

	public Integer getSrcnum() {
		return srcnum;
	}

	public void setSrcnum(Integer srcnum) {
		this.srcnum = srcnum;
	}

	public Integer getPprnum() {
		return pprnum;
	}

	public void setPprnum(Integer pprnum) {
		this.pprnum = pprnum;
	}

	public Integer getPolnum() {
		return polnum;
	}

	public void setPolnum(Integer polnum) {
		this.polnum = polnum;
	}

	public Integer getCscode() {
		return cscode;
	}

	public void setCscode(Integer cscode) {
		this.cscode = cscode;
	}

	public String getTxntyp() {
		return txntyp;
	}

	public void setTxntyp(String txntyp) {
		this.txntyp = txntyp;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDepost() {
		return depost;
	}

	public void setDepost(Double depost) {
		this.depost = depost;
	}

	public Integer getTxnyer() {
		return txnyer;
	}

	public void setTxnyer(Integer txnyer) {
		this.txnyer = txnyer;
	}

	public Integer getTxnmth() {
		return txnmth;
	}

	public void setTxnmth(Integer txnmth) {
		this.txnmth = txnmth;
	}

	public Integer getInsnum() {
		return insnum;
	}

	public void setInsnum(Integer insnum) {
		this.insnum = insnum;
	}

	public String getCreaby() {
		return creaby;
	}

	public void setCreaby(String creaby) {
		this.creaby = creaby;
	}

	public Date getCreadt() {
		return creadt;
	}

	public void setCreadt(Date creadt) {
		this.creadt = creadt;
	}

	public Date getLockin() {
		return lockin;
	}

	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}

	public Integer getPrpseq() {
		return prpseq;
	}

	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}

	public Double getPolfee() {
		return polfee;
	}

	public void setPolfee(Double polfee) {
		this.polfee = polfee;
	}

	public Double getAdmfee() {
		return admfee;
	}

	public void setAdmfee(Double admfee) {
		this.admfee = admfee;
	}

	public Double getTaxamt() {
		return taxamt;
	}

	public void setTaxamt(Double taxamt) {
		this.taxamt = taxamt;
	}

	public Double getOtham1() {
		return otham1;
	}

	public void setOtham1(Double otham1) {
		this.otham1 = otham1;
	}

	public Double getOtham2() {
		return otham2;
	}

	public void setOtham2(Double otham2) {
		this.otham2 = otham2;
	}

	public Double getOtham3() {
		return otham3;
	}

	public void setOtham3(Double otham3) {
		this.otham3 = otham3;
	}

	public Double getOtham4() {
		return otham4;
	}

	public void setOtham4(Double otham4) {
		this.otham4 = otham4;
	}

	public String getChqrel() {
		return chqrel;
	}

	public void setChqrel(String chqrel) {
		this.chqrel = chqrel;
	}

	public String getPaymod() {
		return paymod;
	}

	public void setPaymod(String paymod) {
		this.paymod = paymod;
	}

	public Integer getToptrm() {
		return toptrm;
	}

	public void setToptrm(Integer toptrm) {
		this.toptrm = toptrm;
	}

	public Double getHrbprm() {
		return hrbprm;
	}

	public void setHrbprm(Double hrbprm) {
		this.hrbprm = hrbprm;
	}

	public Integer getPaytrm() {
		return paytrm;
	}

	public void setPaytrm(Integer paytrm) {
		this.paytrm = paytrm;
	}

	public Integer getIcpyer() {
		return icpyer;
	}

	public void setIcpyer(Integer icpyer) {
		this.icpyer = icpyer;
	}

	public Integer getPrcyer() {
		return prcyer;
	}

	public void setPrcyer(Integer prcyer) {
		this.prcyer = prcyer;
	}

	public Double getComper() {
		return comper;
	}

	public void setComper(Double comper) {
		this.comper = comper;
	}

	public Double getComiss() {
		return comiss;
	}

	public void setComiss(Double comiss) {
		this.comiss = comiss;
	}

	public Double getGrsprm() {
		return grsprm;
	}

	public void setGrsprm(Double grsprm) {
		this.grsprm = grsprm;
	}

	public String getPrdcod() {
		return prdcod;
	}

	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}

	public Integer getAdvcod() {
		return advcod;
	}

	public void setAdvcod(Integer advcod) {
		this.advcod = advcod;
	}

	public String getAgncls() {
		return agncls;
	}

	public void setAgncls(String agncls) {
		this.agncls = agncls;
	}

	public Integer getIcpmon() {
		return icpmon;
	}

	public void setIcpmon(Integer icpmon) {
		this.icpmon = icpmon;
	}

	public String getBattyp() {
		return battyp;
	}

	public void setBattyp(String battyp) {
		this.battyp = battyp;
	}

	public Integer getBatcno() {
		return batcno;
	}

	public void setBatcno(Integer batcno) {
		this.batcno = batcno;
	}

	public String getGlintg() {
		return glintg;
	}

	public void setGlintg(String glintg) {
		this.glintg = glintg;
	}

	public Integer getTxnbno() {
		return txnbno;
	}

	public void setTxnbno(Integer txnbno) {
		this.txnbno = txnbno;
	}

	public Date getDuedat() {
		return duedat;
	}

	public void setDuedat(Date duedat) {
		this.duedat = duedat;
	}

	public String getUnlcod() {
		return unlcod;
	}

	public void setUnlcod(String unlcod) {
		this.unlcod = unlcod;
	}

	public String getBrncod() {
		return brncod;
	}

	public void setBrncod(String brncod) {
		this.brncod = brncod;
	}

	public Double getOldprm() {
		return oldprm;
	}

	public void setOldprm(Double oldprm) {
		this.oldprm = oldprm;
	}

	public String getCandoc() {
		return candoc;
	}

	public void setCandoc(String candoc) {
		this.candoc = candoc;
	}

	public Integer getPolyer() {
		return polyer;
	}

	public void setPolyer(Integer polyer) {
		this.polyer = polyer;
	}

	@Override
	public String toString() {
		return "InBillingTransactionsModel [billingTransactionsModelPK=" + billingTransactionsModelPK + ", refdoc="
				+ refdoc + ", refnum=" + refnum + ", srcdoc=" + srcdoc + ", srcnum=" + srcnum + ", pprnum=" + pprnum
				+ ", polnum=" + polnum + ", cscode=" + cscode + ", txntyp=" + txntyp + ", amount=" + amount
				+ ", depost=" + depost + ", txnyer=" + txnyer + ", txnmth=" + txnmth + ", insnum=" + insnum
				+ ", creaby=" + creaby + ", creadt=" + creadt + ", lockin=" + lockin + ", prpseq=" + prpseq
				+ ", polfee=" + polfee + ", admfee=" + admfee + ", taxamt=" + taxamt + ", otham1=" + otham1
				+ ", otham2=" + otham2 + ", otham3=" + otham3 + ", otham4=" + otham4 + ", chqrel=" + chqrel
				+ ", paymod=" + paymod + ", toptrm=" + toptrm + ", hrbprm=" + hrbprm + ", paytrm=" + paytrm
				+ ", icpyer=" + icpyer + ", prcyer=" + prcyer + ", comper=" + comper + ", comiss=" + comiss
				+ ", grsprm=" + grsprm + ", prdcod=" + prdcod + ", advcod=" + advcod + ", agncls=" + agncls
				+ ", icpmon=" + icpmon + ", battyp=" + battyp + ", batcno=" + batcno + ", glintg=" + glintg
				+ ", txnbno=" + txnbno + ", duedat=" + duedat + ", unlcod=" + unlcod + ", brncod=" + brncod
				+ ", oldprm=" + oldprm + ", candoc=" + candoc + ", polyer=" + polyer + "]";
	}

}
