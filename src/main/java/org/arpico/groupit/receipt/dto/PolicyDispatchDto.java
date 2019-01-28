package org.arpico.groupit.receipt.dto;

public class PolicyDispatchDto {

	private String dspdat;
	private String agncod;
	private String agnnam;
	private String ackdat;
	private String cusdat;
	
	public String getDspdat() {
		return dspdat;
	}
	public void setDspdat(String dspdat) {
		this.dspdat = dspdat;
	}
	public String getAgncod() {
		return agncod;
	}
	public void setAgncod(String agncod) {
		this.agncod = agncod;
	}
	public String getAgnnam() {
		return agnnam;
	}
	public void setAgnnam(String agnnam) {
		this.agnnam = agnnam;
	}
	public String getAckdat() {
		return ackdat;
	}
	public void setAckdat(String ackdat) {
		this.ackdat = ackdat;
	}
	public String getCusdat() {
		return cusdat;
	}
	public void setCusdat(String cusdat) {
		this.cusdat = cusdat;
	}
	
	@Override
	public String toString() {
		return "PolicyDispatch [dspdat=" + dspdat + ", agncod=" + agncod + ", agnnam=" + agnnam + ", ackdat=" + ackdat
				+ ", cusdat=" + cusdat + "]";
	}

}
