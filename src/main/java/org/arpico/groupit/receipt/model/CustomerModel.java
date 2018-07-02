package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incustomers")
public class CustomerModel {
	
	private String sbucod;
	private String cscode;
	private String ppdnam;
	private String ppdad1;
	private String ppdad2;
	private String ppdad3;
	private Date ppddob;
	private Integer ppdnag;
	private String ppdsex;
	private String ppdcst;
	private String ppdtel;
	private String ppdeml;
	private String sponam;
	private String creaby;
	private Date creadt;
	private Integer sagnxt;
	private String ntitle;
	private Date spodob;
	private Date lockin;
	private Integer numchl;
	private Double ppdani;
	private String ppdnic;
	private String sponic;

	@Id
	public String getCscode() {
		return cscode;
	}

	public void setCscode(String cscode) {
		this.cscode = cscode;
	}

	public String getSbucod() {
		return sbucod;
	}

	public void setSbucod(String sbucod) {
		this.sbucod = sbucod;
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

	public Date getPpddob() {
		return ppddob;
	}

	public void setPpddob(Date ppddob) {
		this.ppddob = ppddob;
	}

	public Integer getPpdnag() {
		return ppdnag;
	}

	public void setPpdnag(Integer ppdnag) {
		this.ppdnag = ppdnag;
	}

	public String getPpdsex() {
		return ppdsex;
	}

	public void setPpdsex(String ppdsex) {
		this.ppdsex = ppdsex;
	}

	public String getPpdcst() {
		return ppdcst;
	}

	public void setPpdcst(String ppdcst) {
		this.ppdcst = ppdcst;
	}

	public String getPpdtel() {
		return ppdtel;
	}

	public void setPpdtel(String ppdtel) {
		this.ppdtel = ppdtel;
	}

	public String getPpdeml() {
		return ppdeml;
	}

	public void setPpdeml(String ppdeml) {
		this.ppdeml = ppdeml;
	}

	public String getSponam() {
		return sponam;
	}

	public void setSponam(String sponam) {
		this.sponam = sponam;
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

	public Integer getSagnxt() {
		return sagnxt;
	}

	public void setSagnxt(Integer sagnxt) {
		this.sagnxt = sagnxt;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public Date getSpodob() {
		return spodob;
	}

	public void setSpodob(Date spodob) {
		this.spodob = spodob;
	}

	public Date getLockin() {
		return lockin;
	}

	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}

	public Integer getNumchl() {
		return numchl;
	}

	public void setNumchl(Integer numchl) {
		this.numchl = numchl;
	}

	public Double getPpdani() {
		return ppdani;
	}

	public void setPpdani(Double ppdani) {
		this.ppdani = ppdani;
	}

	public String getPpdnic() {
		return ppdnic;
	}

	public void setPpdnic(String ppdnic) {
		this.ppdnic = ppdnic;
	}

	public String getSponic() {
		return sponic;
	}

	public void setSponic(String sponic) {
		this.sponic = sponic;
	}

	@Override
	public String toString() {
		return "CustomerModel [sbucod=" + sbucod + ", cscode=" + cscode + ", ppdnam=" + ppdnam + ", ppdad1=" + ppdad1
				+ ", ppdad2=" + ppdad2 + ", ppdad3=" + ppdad3 + ", ppddob=" + ppddob + ", ppdnag=" + ppdnag
				+ ", ppdsex=" + ppdsex + ", ppdcst=" + ppdcst + ", ppdtel=" + ppdtel + ", ppdeml=" + ppdeml
				+ ", sponam=" + sponam + ", creaby=" + creaby + ", creadt=" + creadt + ", sagnxt=" + sagnxt
				+ ", ntitle=" + ntitle + ", spodob=" + spodob + ", lockin=" + lockin + ", numchl=" + numchl
				+ ", ppdani=" + ppdani + ", ppdnic=" + ppdnic + ", sponic=" + sponic + "]";
	}
}
