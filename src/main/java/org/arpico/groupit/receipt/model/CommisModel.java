package org.arpico.groupit.receipt.model;

public class CommisModel {

	private Double comper;
	private Double comsin;

	public Double getComper() {
		return comper;
	}

	public void setComper(Double comper) {
		this.comper = comper;
	}

	public Double getComsin() {
		return comsin;
	}

	public void setComsin(Double comsin) {
		this.comsin = comsin;
	}

	@Override
	public String toString() {
		return "CommisModel [comper=" + comper + ", comsin=" + comsin + "]";
	}

}
