package org.arpico.groupit.receipt.model;

import java.util.Date;

public class InOcuLoadDetModel {
	private String sbucod;
	private String ocucod;
	private String ridcod;
	private Double lodcls;
	private Double subrat;
	private Double emrate;
	private Double ratmil;
	private Date lockin;
		
	public String getSbucod() {
		return sbucod;
	}

	public void setSbucod(String sbucod) {
		this.sbucod = sbucod;
	}

	public String getOcucod() {
		return ocucod;
	}

	public void setOcucod(String ocucod) {
		this.ocucod = ocucod;
	}

	public String getRidcod() {
		return ridcod;
	}

	public void setRidcod(String ridcod) {
		this.ridcod = ridcod;
	}

	public Date getLockin() {
		return lockin;
	}

	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}

	public Double getLodcls() {
		return lodcls;
	}

	public void setLodcls(Double lodcls) {
		this.lodcls = lodcls;
	}

	public Double getSubrat() {
		return subrat;
	}

	public void setSubrat(Double subrat) {
		this.subrat = subrat;
	}

	public Double getEmrate() {
		return emrate;
	}

	public void setEmrate(Double emrate) {
		this.emrate = emrate;
	}

	public Double getRatmil() {
		return ratmil;
	}

	public void setRatmil(Double ratmil) {
		this.ratmil = ratmil;
	}

	@Override
	public String toString() {
		return "InOcuLoadDetModel [sbucod=" + sbucod + ", ocucod=" + ocucod + ", ridcod=" + ridcod + ", lodcls="
				+ lodcls + ", subrat=" + subrat + ", emrate=" + emrate + ", ratmil=" + ratmil + ", lockin=" + lockin
				+ "]";
	}

	 
	
}
