package org.arpico.groupit.receipt.model;

import java.util.Date;

public class NotRelChequeModel {

	private String receipt;
	private String proposal;
	private String polnum;
	private String ppdnam;
	private Double totprm;
	private String agent;
	private String loccod;
	private String chqnum;
	private Date chqdat;
	private String chqbnk;
	private Date creadt;

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getPolnum() {
		return polnum;
	}

	public void setPolnum(String polnum) {
		this.polnum = polnum;
	}

	public String getPpdnam() {
		return ppdnam;
	}

	public void setPpdnam(String ppdnam) {
		this.ppdnam = ppdnam;
	}

	public Double getTotprm() {
		return totprm;
	}

	public void setTotprm(Double totprm) {
		this.totprm = totprm;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getLoccod() {
		return loccod;
	}

	public void setLoccod(String loccod) {
		this.loccod = loccod;
	}

	public String getChqnum() {
		return chqnum;
	}

	public void setChqnum(String chqnum) {
		this.chqnum = chqnum;
	}

	public Date getChqdat() {
		return chqdat;
	}

	public void setChqdat(Date chqdat) {
		this.chqdat = chqdat;
	}

	public String getChqbnk() {
		return chqbnk;
	}

	public void setChqbnk(String chqbnk) {
		this.chqbnk = chqbnk;
	}

	public Date getCreadt() {
		return creadt;
	}

	public void setCreadt(Date creadt) {
		this.creadt = creadt;
	}


}
