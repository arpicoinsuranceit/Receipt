package org.arpico.groupit.receipt.dto;

public class ReceiptDetailsDto {
	
	private String canDate;
	private String doccod; 
	private String docnum; 
	private String credat;
	private Double topprm;
	private String paymod;
	private String chqNo;
	private String chqrel;
	private String pprnum;
	private String polnum;
	private String name;
	private String user;
	
	public String getCanDate() {
		return canDate;
	}
	public void setCanDate(String canDate) {
		this.canDate = canDate;
	}
	public String getDoccod() {
		return doccod;
	}
	public void setDoccod(String doccod) {
		this.doccod = doccod;
	}
	public String getDocnum() {
		return docnum;
	}
	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}
	public String getCredat() {
		return credat;
	}
	public void setCredat(String credat) {
		this.credat = credat;
	}
	public Double getTopprm() {
		return topprm;
	}
	public void setTopprm(Double topprm) {
		this.topprm = topprm;
	}
	public String getPaymod() {
		return paymod;
	}
	public void setPaymod(String paymod) {
		this.paymod = paymod;
	}
	public String getChqNo() {
		return chqNo;
	}
	public void setChqNo(String chqNo) {
		this.chqNo = chqNo;
	}
	public String getChqrel() {
		return chqrel;
	}
	public void setChqrel(String chqrel) {
		this.chqrel = chqrel;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ReceiptDetailsDto [canDate=" + canDate + ", doccod=" + doccod + ", docnum=" + docnum + ", credat="
				+ credat + ", topprm=" + topprm + ", paymod=" + paymod + ", chqNo=" + chqNo + ", chqrel=" + chqrel
				+ ", pprnum=" + pprnum + ", polnum=" + polnum + ", name=" + name + ", user=" + user + "]";
	}
	
	

}
