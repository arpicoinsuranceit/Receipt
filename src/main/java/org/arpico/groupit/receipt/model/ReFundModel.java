package org.arpico.groupit.receipt.model;

public class ReFundModel {
	
	private Integer pprnum;
	private String doccod;
	private Integer docnum;
	private Double refamount;

	public Integer getPprnum() {
		return pprnum;
	}

	public void setPprnum(Integer pprnum) {
		this.pprnum = pprnum;
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

	public Double getRefamount() {
		return refamount;
	}

	public void setRefamount(Double refamount) {
		this.refamount = refamount;
	}

	@Override
	public String toString() {
		return "ReFundModel [pprnum=" + pprnum + ", doccod=" + doccod + ", docnum=" + docnum + ", refamount="
				+ refamount + "]";
	}

}
