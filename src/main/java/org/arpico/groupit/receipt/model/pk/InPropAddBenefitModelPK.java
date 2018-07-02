package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropAddBenefitModelPK implements Serializable{
	
	protected String sbucod;
	protected String loccod;
	protected Integer pprnum;
	protected String ridcod;
	protected Integer prpseq;
	
	public InPropAddBenefitModelPK() {
	}
	
	public InPropAddBenefitModelPK(String sbucod, String loccod, Integer pprnum, String ridcod, Integer prpseq) {
		super();
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
