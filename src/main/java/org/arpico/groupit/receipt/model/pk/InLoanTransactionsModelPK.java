package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InLoanTransactionsModelPK implements Serializable{
	private String sbucod;
	private String loccod;
	private String doccod;
	private Integer docnum;
	private Integer linnum;
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
	public String getDoccod() {
		return doccod;
	}
	public void setDoccod(String doccod) {
		this.doccod = doccod;
	}
	public Integer getDocnum() {
		return docnum;
	}
	public void setDocnum(Integer docnum) {
		this.docnum = docnum;
	}
	public Integer getLinnum() {
		return linnum;
	}
	public void setLinnum(Integer linnum) {
		this.linnum = linnum;
	}
	@Override
	public String toString() {
		return "InLoanTransactionsModelPK [sbucod=" + sbucod + ", loccod=" + loccod + ", doccod=" + doccod + ", docnum="
				+ docnum + ", linnum=" + linnum + "]";
	}

}
