package org.arpico.groupit.receipt.model;

import java.util.Date;

public class WorkFlowPolicyGridModel {

	private String proposal;
	private String policy;
	private String pprNum;
	private Date duedat;
	private Double totprm;
	private String ppdini;
	private String agent;
	private String brncod;
	private String health;

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getPprNum() {
		return pprNum;
	}

	public void setPprNum(String pprNum) {
		this.pprNum = pprNum;
	}

	public Date getDuedat() {
		return duedat;
	}

	public void setDuedat(Date duedat) {
		this.duedat = duedat;
	}

	public Double getTotprm() {
		return totprm;
	}

	public void setTotprm(Double totprm) {
		this.totprm = totprm;
	}

	public String getPpdini() {
		return ppdini;
	}

	public void setPpdini(String ppdini) {
		this.ppdini = ppdini;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getBrncod() {
		return brncod;
	}

	public void setBrncod(String brncod) {
		this.brncod = brncod;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@Override
	public String toString() {
		return "WorkFlowPolicyGridModel [proposal=" + proposal + ", policy=" + policy + ", pprNum=" + pprNum
				+ ", duedat=" + duedat + ", totprm=" + totprm + ", ppdini=" + ppdini + ", agent=" + agent + ", brncod="
				+ brncod + ", health=" + health + "]";
	}
}
