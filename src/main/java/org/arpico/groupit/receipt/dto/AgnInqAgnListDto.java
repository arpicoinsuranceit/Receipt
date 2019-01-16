package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class AgnInqAgnListDto {

	private Integer agncod;
	private String agnnam;
	private String agnnic;
	private String agnsta;
	private String sliirg;
	private String supvid;
	private Date agndob;
	private String subdcd;
	private Date agnrdt;

	public Integer getAgncod() {
		return agncod;
	}

	public void setAgncod(Integer agncod) {
		this.agncod = agncod;
	}

	public String getAgnnam() {
		return agnnam;
	}

	public void setAgnnam(String agnnam) {
		this.agnnam = agnnam;
	}

	public String getAgnnic() {
		return agnnic;
	}

	public void setAgnnic(String agnnic) {
		this.agnnic = agnnic;
	}

	public String getAgnsta() {
		return agnsta;
	}

	public void setAgnsta(String agnsta) {
		this.agnsta = agnsta;
	}

	public String getSliirg() {
		return sliirg;
	}

	public void setSliirg(String sliirg) {
		this.sliirg = sliirg;
	}

	public String getSupvid() {
		return supvid;
	}

	public void setSupvid(String supvid) {
		this.supvid = supvid;
	}

	public Date getAgndob() {
		return agndob;
	}

	public void setAgndob(Date agndob) {
		this.agndob = agndob;
	}

	public String getSubdcd() {
		return subdcd;
	}

	public void setSubdcd(String subdcd) {
		this.subdcd = subdcd;
	}

	public Date getAgnrdt() {
		return agnrdt;
	}

	public void setAgnrdt(Date agnrdt) {
		this.agnrdt = agnrdt;
	}

	@Override
	public String toString() {
		return "AgnInqAgnListDto [agncod=" + agncod + ", agnnam=" + agnnam + ", agnnic=" + agnnic + ", agnsta=" + agnsta
				+ ", sliirg=" + sliirg + ", supvid=" + supvid + ", agndob=" + agndob + ", subdcd=" + subdcd
				+ ", agnrdt=" + agnrdt + "]";
	}

}
