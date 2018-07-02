package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InTransactionsModelPK;

@Entity
@Table(name = "intransactions")
public class InTransactionsModel {
	private InTransactionsModelPK inTransactionsModelPK;
	private String cscode;
	private Integer quonum;
	private String pprnum;
	private Integer polnum;
	private Double totprm;
	private String creaby;
	private Date creadt;
	private Integer seqnum;
	private String paymod;
	private String remark;
	private String amtwrd;
	private Date lockin;
	private String chqnum;
	private String chqbnk;
	private Date chqdat;
	private String ccdnum;
	private String bancod;
	private String advcod;
	private String ntitle;
	private String ppdnam;
	private String ppdad1;
	private String ppdad2;
	private String ppdad3;
	private String rctsta;
	private Date txndat;
	private String chqrel;
	private Date reldat;
	private String bilpik;
	private Date bildat;
	private String compad;
	
	@EmbeddedId
	public InTransactionsModelPK getInTransactionsModelPK() {
		return inTransactionsModelPK;
	}



	public void setInTransactionsModelPK(InTransactionsModelPK inTransactionsModelPK) {
		this.inTransactionsModelPK = inTransactionsModelPK;
	}



	public String getCscode() {
		return cscode;
	}



	public void setCscode(String cscode) {
		this.cscode = cscode;
	}



	public Integer getQuonum() {
		return quonum;
	}



	public void setQuonum(Integer quonum) {
		this.quonum = quonum;
	}



	public String getPprnum() {
		return pprnum;
	}



	public void setPprnum(String pprnum) {
		this.pprnum = pprnum;
	}



	public Integer getPolnum() {
		return polnum;
	}



	public void setPolnum(Integer polnum) {
		this.polnum = polnum;
	}



	public Double getTotprm() {
		return totprm;
	}



	public void setTotprm(Double totprm) {
		this.totprm = totprm;
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



	public Integer getSeqnum() {
		return seqnum;
	}



	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}



	public String getPaymod() {
		return paymod;
	}



	public void setPaymod(String paymod) {
		this.paymod = paymod;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getAmtwrd() {
		return amtwrd;
	}



	public void setAmtwrd(String amtwrd) {
		this.amtwrd = amtwrd;
	}



	public Date getLockin() {
		return lockin;
	}



	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}



	public String getChqnum() {
		return chqnum;
	}



	public void setChqnum(String chqnum) {
		this.chqnum = chqnum;
	}



	public String getChqbnk() {
		return chqbnk;
	}



	public void setChqbnk(String chqbnk) {
		this.chqbnk = chqbnk;
	}



	public Date getChqdat() {
		return chqdat;
	}



	public void setChqdat(Date chqdat) {
		this.chqdat = chqdat;
	}



	public String getCcdnum() {
		return ccdnum;
	}



	public void setCcdnum(String ccdnum) {
		this.ccdnum = ccdnum;
	}



	public String getBancod() {
		return bancod;
	}



	public void setBancod(String bancod) {
		this.bancod = bancod;
	}



	public String getAdvcod() {
		return advcod;
	}



	public void setAdvcod(String advcod) {
		this.advcod = advcod;
	}



	public String getNtitle() {
		return ntitle;
	}



	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}



	public String getPpdnam() {
		return ppdnam;
	}



	public void setPpdnam(String ppdnam) {
		this.ppdnam = ppdnam;
	}



	public String getPpdad1() {
		return ppdad1;
	}



	public void setPpdad1(String ppdad1) {
		this.ppdad1 = ppdad1;
	}



	public String getPpdad2() {
		return ppdad2;
	}



	public void setPpdad2(String ppdad2) {
		this.ppdad2 = ppdad2;
	}



	public String getPpdad3() {
		return ppdad3;
	}



	public void setPpdad3(String ppdad3) {
		this.ppdad3 = ppdad3;
	}



	public String getRctsta() {
		return rctsta;
	}



	public void setRctsta(String rctsta) {
		this.rctsta = rctsta;
	}



	public Date getTxndat() {
		return txndat;
	}



	public void setTxndat(Date txndat) {
		this.txndat = txndat;
	}



	public String getChqrel() {
		return chqrel;
	}



	public void setChqrel(String chqrel) {
		this.chqrel = chqrel;
	}



	public Date getReldat() {
		return reldat;
	}



	public void setReldat(Date reldat) {
		this.reldat = reldat;
	}



	public String getBilpik() {
		return bilpik;
	}



	public void setBilpik(String bilpik) {
		this.bilpik = bilpik;
	}



	public Date getBildat() {
		return bildat;
	}



	public void setBildat(Date bildat) {
		this.bildat = bildat;
	}



	public String getCompad() {
		return compad;
	}



	public void setCompad(String compad) {
		this.compad = compad;
	}



	@Override
	public String toString() {
		return "InTransactionsModel [ cscode=" + cscode + ", quonum=" + quonum + ", pprnum=" + pprnum + ", polnum=" + polnum
				+ ", totprm=" + totprm + ", creaby=" + creaby + ", creadt=" + creadt
				+ ", seqnum=" + seqnum + ", paymod=" + paymod + ", remark=" + remark + ", amtwrd=" + amtwrd
				+ ", lockin=" + lockin + ", chqnum=" + chqnum + ", chqbnk=" + chqbnk + ", chqdat=" + chqdat
				+ ", ccdnum=" + ccdnum + ", bancod=" + bancod + ", advcod=" + advcod + ", ntitle=" + ntitle
				+ ", ppdnam=" + ppdnam + ", ppdad1=" + ppdad1 + ", ppdad2=" + ppdad2 + ", ppdad3=" + ppdad3
				+ ", rctsta=" + rctsta + ", txndat=" + txndat + ", chqrel=" + chqrel + ", reldat=" + reldat
				+ ", bilpik=" + bilpik + ", bildat=" + bildat + ", compad=" + compad + "]";
	}
}
