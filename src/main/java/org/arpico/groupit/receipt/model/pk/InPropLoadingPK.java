package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropLoadingPK implements Serializable{
	
	private String sbucod;
	private String loccod;
	private Integer pprnum;
	private String ridcod;
	private Integer prpseq;
	
	public InPropLoadingPK() {}
	
	public InPropLoadingPK(String sbucod, String loccod, Integer pprnum, String ridcod, Integer prpseq) {
		this.sbucod = sbucod;
		this.loccod = loccod;
		this.pprnum = pprnum;
		this.ridcod = ridcod;
		this.prpseq = prpseq;
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

	public Integer getPrpseq() {
		return prpseq;
	}

	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}
	
	
	
}
