package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropAddBenefitPK implements Serializable{
	protected String sbucod;
	protected String loccod;
	protected Integer pprnum;
	protected String ridcod;
	
	public InPropAddBenefitPK() {
	}
	
	public InPropAddBenefitPK(String sbucod, String loccod, Integer pprnum, String ridcod) {
		this.sbucod = sbucod;
		this.loccod = loccod;
		this.pprnum = pprnum;
		this.ridcod = ridcod;
	}

	public String getSbucod() {
		return sbucod;
	}

	public void setSbucod(String sbucod) {
		this.sbucod = sbucod;
	}

	public String getLoccod() {
		return loccod;
	}

	public void setLoccod(String loccod) {
		this.loccod = loccod;
	}

	public Integer getPprnum() {
		return pprnum;
	}

	public void setPprnum(Integer pprnum) {
		this.pprnum = pprnum;
	}

	public String getRidcod() {
		return ridcod;
	}

	public void setRidcod(String ridcod) {
		this.ridcod = ridcod;
	}
	
	
	
}
