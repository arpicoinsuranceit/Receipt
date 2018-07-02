package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropMedicalReqModelPK;

@Entity
@Table(name = "inpropmedicalreq")
public class InPropMedicalReqModel {
	
	private InPropMedicalReqModelPK inPropMedicalReqModelPK;
	private String mednam;
	private String tessta;	
	private Date lockin;	
	private String hoscod;	
	private String paysta;	
	private String medorg;	
	private Date rcddat;	
	private Double payamt;	
	private String addnot;
	private String glbtch;
	@EmbeddedId
	public InPropMedicalReqModelPK getInPropMedicalReqModelPK() {
		return inPropMedicalReqModelPK;
	}
	public void setInPropMedicalReqModelPK(InPropMedicalReqModelPK inPropMedicalReqModelPK) {
		this.inPropMedicalReqModelPK = inPropMedicalReqModelPK;
	}
	public String getMednam() {
		return mednam;
	}
	public void setMednam(String mednam) {
		this.mednam = mednam;
	}
	public String getTessta() {
		return tessta;
	}
	public void setTessta(String tessta) {
		this.tessta = tessta;
	}
	public Date getLockin() {
		return lockin;
	}
	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}
	public String getHoscod() {
		return hoscod;
	}
	public void setHoscod(String hoscod) {
		this.hoscod = hoscod;
	}
	public String getPaysta() {
		return paysta;
	}
	public void setPaysta(String paysta) {
		this.paysta = paysta;
	}
	public String getMedorg() {
		return medorg;
	}
	public void setMedorg(String medorg) {
		this.medorg = medorg;
	}
	public Date getRcddat() {
		return rcddat;
	}
	public void setRcddat(Date rcddat) {
		this.rcddat = rcddat;
	}
	public Double getPayamt() {
		return payamt;
	}
	public void setPayamt(Double payamt) {
		this.payamt = payamt;
	}
	public String getAddnot() {
		return addnot;
	}
	public void setAddnot(String addnot) {
		this.addnot = addnot;
	}
	public String getGlbtch() {
		return glbtch;
	}
	public void setGlbtch(String glbtch) {
		this.glbtch = glbtch;
	}
	@Override
	public String toString() {
		return "InPropMedicalReqModel [inPropMedicalReqModelPK=" + inPropMedicalReqModelPK + ", mednam=" + mednam
				+ ", tessta=" + tessta + ", lockin=" + lockin + ", hoscod=" + hoscod + ", paysta=" + paysta
				+ ", medorg=" + medorg + ", rcddat=" + rcddat + ", payamt=" + payamt + ", addnot=" + addnot
				+ ", glbtch=" + glbtch + "]";
	}	
	
}
