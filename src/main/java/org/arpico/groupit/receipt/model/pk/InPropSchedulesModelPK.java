package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropSchedulesModelPK implements Serializable {
	
	protected String sbucod;
	protected String loccod;
	protected Integer pprnum;
	protected Integer prpseq;
	protected Integer polyer;
	
	public InPropSchedulesModelPK() { }
	
	public InPropSchedulesModelPK(String sbucod, String loccod, Integer pprnum, Integer prpseq, Integer polyer) {
		this.sbucod = sbucod;
		this.loccod = loccod;
		this.pprnum = pprnum;
		this.prpseq = prpseq;
		this.polyer = polyer;
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
	public Integer getPrpseq() {
		return prpseq;
	}
	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}
	public Integer getPolyer() {
		return polyer;
	}
	public void setPolyer(Integer polyer) {
		this.polyer = polyer;
	}
	
}
