package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropMedicalReqModelPK implements Serializable{

	private String sbucod;	
	private String loccod;	
	private Integer pprnum;	
	private String medcod;	
	private String instyp;	
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
	public String getMedcod() {
		return medcod;
	}
	public void setMedcod(String medcod) {
		this.medcod = medcod;
	}
	public String getInstyp() {
		return instyp;
	}
	public void setInstyp(String instyp) {
		this.instyp = instyp;
	}
	public Integer getPrpseq() {
		return prpseq;
	}
	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}
	
	@Override
	public String toString() {
		return "InPropMedicalReqModelPK [sbucod=" + sbucod + ", loccod=" + loccod + ", pprnum=" + pprnum + ", medcod="
				+ medcod + ", instyp=" + instyp + ", prpseq=" + prpseq + "]";
	}
	
}
