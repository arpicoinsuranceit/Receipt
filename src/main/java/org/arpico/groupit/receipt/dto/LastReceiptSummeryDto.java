package org.arpico.groupit.receipt.dto;

public class LastReceiptSummeryDto {
	
	private String doccod;
	private String doctyp;
	private String creadt;
	private String pprnum;
	private String polnum;
	private Double amount;
	private Double paymod;
	private String chqrel;
	
	public String getDoccod() {
		return doccod;
	}
	public void setDoccod(String doccod) {
		this.doccod = doccod;
	}
	public String getDoctyp() {
		return doctyp;
	}
	public void setDoctyp(String doctyp) {
		this.doctyp = doctyp;
	}
	public String getCreadt() {
		return creadt;
	}
	public void setCreadt(String creadt) {
		this.creadt = creadt;
	}
	public String getPprnum() {
		return pprnum;
	}
	public void setPprnum(String pprnum) {
		this.pprnum = pprnum;
	}
	public String getPolnum() {
		return polnum;
	}
	public void setPolnum(String polnum) {
		this.polnum = polnum;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getPaymod() {
		return paymod;
	}
	public void setPaymod(Double paymod) {
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
		return "LastReceiptSummeryDto [doccod=" + doccod + ", doctyp=" + doctyp + ", creadt=" + creadt + ", pprnum="
				+ pprnum + ", polnum=" + polnum + ", amount=" + amount + ", paymod=" + paymod + ", chqrel=" + chqrel
				+ "]";
	}
}