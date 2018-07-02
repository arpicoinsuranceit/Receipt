package org.arpico.groupit.receipt.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropSurrenderValsPK;

@Entity
@Table(name = "inpropsurrendervals")
public class InPropSurrenderValsModel {

	private InPropSurrenderValsPK inPropSurrenderValsPK;
	private String advcod;
	private Double isumas;
	private Double mature;
	private Double paidup;
	private String polnum;
	private Double prmpad;
	private Double prmpyr;
	private Double surrnd;
	
	
	@EmbeddedId
	public InPropSurrenderValsPK getInPropSurrenderValsPK() {
		return inPropSurrenderValsPK;
	}
	public void setInPropSurrenderValsPK(InPropSurrenderValsPK inPropSurrenderValsPK) {
		this.inPropSurrenderValsPK = inPropSurrenderValsPK;
	}
	
	
	public String getAdvcod() {
		return advcod;
	}
	public void setAdvcod(String advcod) {
		this.advcod = advcod;
	}
	public Double getIsumas() {
		return isumas;
	}
	public void setIsumas(Double isumas) {
		this.isumas = isumas;
	}
	public Double getMature() {
		return mature;
	}
	public void setMature(Double mature) {
		this.mature = mature;
	}
	public Double getPaidup() {
		return paidup;
	}
	public void setPaidup(Double paidup) {
		this.paidup = paidup;
	}
	public String getPolnum() {
		return polnum;
	}
	public void setPolnum(String polnum) {
		this.polnum = polnum;
	}
	public Double getPrmpad() {
		return prmpad;
	}
	public void setPrmpad(Double prmpad) {
		this.prmpad = prmpad;
	}
	public Double getPrmpyr() {
		return prmpyr;
	}
	public void setPrmpyr(Double prmpyr) {
		this.prmpyr = prmpyr;
	}
	public Double getSurrnd() {
		return surrnd;
	}
	public void setSurrnd(Double surrnd) {
		this.surrnd = surrnd;
	}
	@Override
	public String toString() {
		return "InPropSurrenderValsModel [inPropSurrenderValsPK=" + inPropSurrenderValsPK + ", advcod=" + advcod
				+ ", isumas=" + isumas + ", mature=" + mature + ", paidup=" + paidup + ", polnum=" + polnum
				+ ", prmpad=" + prmpad + ", prmpyr=" + prmpyr + ", surrnd=" + surrnd + "]";
	}
	
}
