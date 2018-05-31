package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.arpico.groupit.receipt.util.AppConstant;

@Embeddable
public class InProposalsModelPK implements Serializable	{
	
	protected String sbucod = AppConstant.SBU_CODE;
	protected String loccod;
	private String pprnum;
	private String doccod;
	private Integer prpseq = AppConstant.ZERO;
	
	public InProposalsModelPK() {	}

	public InProposalsModelPK(String sbucod, String loccod, String pprnum, String doccod, Integer prpseq) {
		this.sbucod = sbucod;
		this.loccod = loccod;
		this.pprnum = pprnum;
		this.doccod = doccod;
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

	public String getPprnum() {
		return pprnum;
	}

	public void setPprnum(String pprnum) {
		this.pprnum = pprnum;
	}

	public String getDoccod() {
		return doccod;
	}

	public void setDoccod(String doccod) {
		this.doccod = doccod;
	}

	public Integer getPrpseq() {
		return prpseq;
	}

	public void setPrpseq(Integer prpseq) {
		this.prpseq = prpseq;
	}

	@Override
	public String toString() {
		return "InProposalsModelPK [sbucod=" + sbucod + ", loccod=" + loccod + ", pprnum=" + pprnum + ", doccod="
				+ doccod + ", prpseq=" + prpseq + "]";
	}
	
	
	
	
}
