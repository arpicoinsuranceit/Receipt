package org.arpico.groupit.receipt.dto;

public class ProposalL3Dto {
	private String pprnum;
	private Double totprm;
	private Double payment;
	private Integer reqcnt;

	public String getPprnum() {
		return pprnum;
	}
	public void setPprnum(String pprnum) {
		this.pprnum = pprnum;
	}
	public Double getTotprm() {
		return totprm;
	}
	public void setTotprm(Double totprm) {
		this.totprm = totprm;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Integer getReqcnt() {
		return reqcnt;
	}
	public void setReqcnt(Integer reqcnt) {
		this.reqcnt = reqcnt;
	}
	@Override
	public String toString() {
		return "ProposalL3Dto [pprnum=" + pprnum + ", totprm=" + totprm + ", payment=" + payment + ", reqcnt=" + reqcnt
				+ "]";
	}
	
	
}
