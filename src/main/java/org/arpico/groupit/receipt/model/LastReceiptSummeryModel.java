package org.arpico.groupit.receipt.model;

import java.util.Date;

public class LastReceiptSummeryModel {

	private String doccod;
	private Integer docnum;
	private Date creadt;
	private String pprnum;
	private Integer polnum;
	private Double totprm;
	private String paymod;
	private String chqrel;
	
	public String getDoccod() {
		return doccod;
	}
	public void setDoccod(String doccod) {
		this.doccod = doccod;
	}
	public Integer getDocnum() {
		return docnum;
	}
	public void setDocnum(Integer docnum) {
		this.docnum = docnum;
	}
	public Date getCreadt() {
		return creadt;
	}
	public void setCreadt(Date creadt) {
		this.creadt = creadt;
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
	public String getPaymod() {
		return paymod;
	}
	public void setPaymod(String paymod) {
		this.paymod = paymod;
	}
	public String getChqrel() {
		return chqrel;
	}
	public void setChqrel(String chqrel) {
		this.chqrel = chqrel;
	}
	@Override
	public String toString() {
		return "LastReceiptSummeryModel [doccod=" + doccod + ", docnum=" + docnum + ", creadt=" + creadt + ", pprnum="
				+ pprnum + ", polnum=" + polnum + ", totprm=" + totprm + ", paymod=" + paymod + ", chqrel=" + chqrel
				+ "]";
	}
	
}
