package org.arpico.groupit.receipt.model.pk;


import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropNomDetailsModelPK implements Serializable{
	
	private String sbucod;
	private String loccod;
	private Integer pprnum;
	private String nomnam;
	private Integer prpseq;
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
	public String getNomnam() {
		return nomnam;
	}
	public void setNomnam(String nomnam) {
		this.nomnam = nomnam;
	}
	public Integer getPrpseq() {
		return prpseq;
	}
	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}
	@Override
	public String toString() {
		return "InPropNomDetailsModel [sbucod=" + sbucod + ", loccod=" + loccod + ", pprnum=" + pprnum + ", nomnam="
				+ nomnam + ", prpseq=" + prpseq + "]";
	}
	
	
}
