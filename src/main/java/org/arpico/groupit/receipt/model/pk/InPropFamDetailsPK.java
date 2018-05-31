package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropFamDetailsPK implements Serializable{
	private String sbucod;
	private String loccod;
	private Integer pprnum;
	private String fmlnam;
	private Integer prpseq;
	
	public InPropFamDetailsPK() {}

	public InPropFamDetailsPK(String sbucod, String loccod, Integer pprnum, String fmlnam,
			Integer prpseq) {
		this.sbucod = sbucod;
		this.loccod = loccod;
		this.pprnum = pprnum;
		this.fmlnam = fmlnam;
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

	public String getFmlnam() {
		return fmlnam;
	}

	public void setFmlnam(String fmlnam) {
		this.fmlnam = fmlnam;
	}
	
	public Integer getPrpseq() {
		return prpseq;
	}

	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}

	@Override
	public String toString() {
		return "InPropFamDetailsPK [sbucod=" + sbucod + ", loccod=" + loccod + ", pprnum=" + pprnum + ", fmlnam="
				+ fmlnam + ", prpseq=" + prpseq + "]";
	} 
	
	
	
}
