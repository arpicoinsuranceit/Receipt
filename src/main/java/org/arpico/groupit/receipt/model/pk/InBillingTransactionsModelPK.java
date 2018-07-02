package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class InBillingTransactionsModelPK implements Serializable {
	private String sbucod;
	private String loccod;
	private String doccod;
	private Integer docnum;
	private Integer linnum;
	private Date txndat;
	
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
	public Date getTxndat() {
		return txndat;
	}
	public void setTxndat(Date txndat) {
		this.txndat = txndat;
	}
	@Override
	public String toString() {
		return "InBillingTransactionsModelPK [sbucod=" + sbucod + ", loccod=" + loccod + ", doccod=" + doccod
				+ ", docnum=" + docnum + ", linnum=" + linnum + ", txndat=" + txndat + "]";
	}

}
