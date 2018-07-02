package org.arpico.groupit.receipt.model;

import java.util.Date;

public class InOcuLoadDetModel {
	private String sbucod;
	private String ocucod;
	private String ridcod;
	private String lodcls;
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

	public String getLodcls() {
		return lodcls;
	}

	public void setLodcls(String lodcls) {
		this.lodcls = lodcls;
	}

	public Date getLockin() {
		return lockin;
	}

	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}

	@Override
	public String toString() {
		return "InOcuLoadDet [sbucod=" + sbucod + ", ocucod=" + ocucod + ", ridcod=" + ridcod + ", lodcls=" + lodcls
				+ ", lockin=" + lockin + "]";
	}
	
	
}
