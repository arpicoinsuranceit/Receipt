package org.arpico.groupit.receipt.dto;

public class SurrenderValsDto {

	private Integer id;
	private Integer padtrm;
	private Double isumas;
	private Double paidup;
	private Double surrnd;
	private Double mature;
	private Double prmpyr;
	private Double prmpad;
	private Integer polyer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPadtrm() {
		return padtrm;
	}

	public void setPadtrm(Integer padtrm) {
		this.padtrm = padtrm;
	}

	public Double getIsumas() {
		return isumas;
	}

	public void setIsumas(Double isumas) {
		this.isumas = isumas;
	}

	public Double getPaidup() {
		return paidup;
	}

	public void setPaidup(Double paidup) {
		this.paidup = paidup;
	}

	public Double getSurrnd() {
		return surrnd;
	}

	public void setSurrnd(Double surrnd) {
		this.surrnd = surrnd;
	}

	public Double getMature() {
		return mature;
	}

	public void setMature(Double mature) {
		this.mature = mature;
	}

	public Double getPrmpyr() {
		return prmpyr;
	}

	public void setPrmpyr(Double prmpyr) {
		this.prmpyr = prmpyr;
	}

	public Double getPrmpad() {
		return prmpad;
	}

	public void setPrmpad(Double prmpad) {
		this.prmpad = prmpad;
	}

	public Integer getPolyer() {
		return polyer;
	}

	public void setPolyer(Integer polyer) {
		this.polyer = polyer;
	}

	@Override
	public String toString() {
		return "SurrenderValsDto [id=" + id + ", padtrm=" + padtrm + ", isumas=" + isumas + ", paidup=" + paidup
				+ ", surrnd=" + surrnd + ", mature=" + mature + ", prmpyr=" + prmpyr + ", prmpad=" + prmpad
				+ ", polyer=" + polyer + "]";
	}
	
	
}
