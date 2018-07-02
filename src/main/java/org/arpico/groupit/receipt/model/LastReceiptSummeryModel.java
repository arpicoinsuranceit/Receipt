package org.arpico.groupit.receipt.model;

import java.util.Date;

public class LastReceiptSummeryModel {

	private String doccod;
	private Integer docnum;
	private Date creadt;
	private String pprnum;
	private Integer polnum;
	private Double totprm;
	
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
	@Override
	public String toString() {
		return "LastReceiptSummeryModel [doccod=" + doccod + ", docnum=" + docnum + ", creadt=" + creadt + ", pprnum="
				+ pprnum + ", polnum=" + polnum + ", totprm=" + totprm + "]";
	}
}
