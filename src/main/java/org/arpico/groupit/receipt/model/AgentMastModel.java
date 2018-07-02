package org.arpico.groupit.receipt.model;

public class AgentMastModel {
	
	private String agncls;
	private String unlcod;
	private String location;
	private String brnmanager;
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
	public String getBrnmanager() {
		return brnmanager;
	}
	public void setBrnmanager(String brnmanager) {
		this.brnmanager = brnmanager;
	}
	@Override
	public String toString() {
		return "AgentMastDao [agncls=" + agncls + ", unlcod=" + unlcod + ", location=" + location + ", brnmanager="
				+ brnmanager + "]";
	}

}
