package org.arpico.groupit.receipt.model;

public class AgentMastModel {

	private String agncls;
	private String unlcod;
	private String location;

	public String getAgncls() {
		return agncls;
	}

	public void setAgncls(String agncls) {
		this.agncls = agncls;
	}

	public String getUnlcod() {
		return unlcod;
	}

	public void setUnlcod(String unlcod) {
		this.unlcod = unlcod;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "AgentMastModel [agncls=" + agncls + ", unlcod=" + unlcod + ", location=" + location + "]";
	}

}
