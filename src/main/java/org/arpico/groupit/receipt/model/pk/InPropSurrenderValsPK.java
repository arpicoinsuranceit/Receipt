package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InPropSurrenderValsPK implements Serializable{

	private String sbucod;	
	private Integer quonum;	
	private String pprnum;	
	private Integer prpseq;	
	private String padtrm;	
	private Integer polyer;
	
	public String getSbucod() {
		return sbucod;
	}
	public void setSbucod(String sbucod) {
		this.sbucod = sbucod;
	}
	public Integer getQuonum() {
		return quonum;
	}
	public void setQuonum(Integer quonum) {
		this.quonum = quonum;
	}
	public String getPprnum() {
		return pprnum;
	}
	public void setPprnum(String pprnum) {
		this.pprnum = pprnum;
	}
	public Integer getPrpseq() {
		return prpseq;
	}
	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}
	public String getPadtrm() {
		return padtrm;
	}
	public void setPadtrm(String padtrm) {
		this.padtrm = padtrm;
	}
	public Integer getPolyer() {
		return polyer;
	}
	public void setPolyer(Integer polyer) {
		this.polyer = polyer;
	}
	
	@Override
	public String toString() {
		return "InPropSurrenderValsPK [sbucod=" + sbucod + ", quonum=" + quonum + ", pprnum=" + pprnum + ", prpseq="
				+ prpseq + ", padtrm=" + padtrm + ", polyer=" + polyer + "]";
	}
}
