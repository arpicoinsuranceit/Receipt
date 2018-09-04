package org.arpico.groupit.receipt.model;

import java.util.Date;

public class ReceiptDetailsModel {
	
	private String doccod; 
	private Integer docnum; 
	private Date credat;
	private Double topprm;
	private String paymod;
	private String chqNo;
	private String chqrel;
	private String pprnum;
	private Integer polnum;
	private String name;
	private String user;
	

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
	public Date getCredat() {
		return credat;
	}
	public void setCredat(Date credat) {
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
	public Integer getPolnum() {
		return polnum;
	}
	public void setPolnum(Integer polnum) {
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
		return "ReceiptDetailsModel [doccod=" + doccod + ", docnum=" + docnum + ", credat=" + credat + ", topprm="
				+ topprm + ", paymod=" + paymod + ", chqNo=" + chqNo + ", chqrel=" + chqrel + ", pprnum=" + pprnum
				+ ", polnum=" + polnum + ", name=" + name + ", user=" + user + "]";
	}
	
	
	

}
