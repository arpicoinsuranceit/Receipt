package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropPrePolsModelPK implements Serializable{
	private String sbucod;
	private String loccod;
	private Integer pprnum;
	private String polnum;
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
	public String getPolnum() {
		return polnum;
	}
	public void setPolnum(String polnum) {
		this.polnum = polnum;
	}
	public Integer getPrpseq() {
		return prpseq;
	}
	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}
	
	@Override
	public String toString() {
		return "InPropPrePolsPK [sbucod=" + sbucod + ", loccod=" + loccod + ", pprnum=" + pprnum + ", polnum=" + polnum
				+ ", prpseq=" + prpseq + "]";
	}
}
