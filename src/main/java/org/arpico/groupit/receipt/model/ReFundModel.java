package org.arpico.groupit.receipt.model;

public class ReFundModel {
	
	private Integer pprnum;
	private String doccod;
	private Integer docnum;
	private Double refamount;
	private Integer linnum;
	private String paymode;
	
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

	public Integer getLinnum() {
		return linnum;
	}

	public void setLinnum(Integer linnum) {
		this.linnum = linnum;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	@Override
	public String toString() {
		return "ReFundModel [pprnum=" + pprnum + ", doccod=" + doccod + ", docnum=" + docnum + ", refamount="
				+ refamount + ", linnum=" + linnum + ", paymode=" + paymode + "]";
	}


}
