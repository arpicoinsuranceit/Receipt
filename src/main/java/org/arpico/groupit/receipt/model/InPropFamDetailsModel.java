package org.arpico.groupit.receipt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropFamDetailsModelPK;
import org.arpico.groupit.receipt.util.AppConstant;

@Entity
@Table (name = "inpropfamdetails")
public class InPropFamDetailsModel implements Serializable {

	private InPropFamDetailsModelPK inPropFamDetailsPK;
	private String fmlrel;
	private Date fmldob;
	private String fmlnic;
	private Date lockin = AppConstant.DATE;
	private String fmlsex;
	private Double fmlshr;
	private Float fmlage;
	private String cicapp = "N";
	private String hrbapp = "N";
	private String hbcapp = "N";
	private String shrbap = "N";
	
	@EmbeddedId
	public InPropFamDetailsModelPK getInPropFamDetailsPK() {
		return inPropFamDetailsPK;
	}

	public void setInPropFamDetailsPK(InPropFamDetailsModelPK inPropFamDetailsPK) {
		this.inPropFamDetailsPK = inPropFamDetailsPK;
	}

	public String getFmlrel() {
		return fmlrel;
	}

	public void setFmlrel(String fmlrel) {
		this.fmlrel = fmlrel;
	}

	public Date getFmldob() {
		return fmldob;
	}

	public void setFmldob(Date fmldob) {
		this.fmldob = fmldob;
	}

	public String getFmlnic() {
		return fmlnic;
	}

	public void setFmlnic(String fmlnic) {
		this.fmlnic = fmlnic;
	}

	public Date getLockin() {
		return lockin;
	}

	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}

	public String getFmlsex() {
		return fmlsex;
	}

	public void setFmlsex(String fmlsex) {
		this.fmlsex = fmlsex;
	}

	public Double getFmlshr() {
		return fmlshr;
	}

	public void setFmlshr(Double fmlshr) {
		this.fmlshr = fmlshr;
	}

	public Float getFmlage() {
		return fmlage;
	}

	public void setFmlage(Float fmlage) {
		this.fmlage = fmlage;
	}

	public String getCicapp() {
		return cicapp;
	}

	public void setCicapp(String cicapp) {
		this.cicapp = cicapp;
	}

	public String getHrbapp() {
		return hrbapp;
	}

	public void setHrbapp(String hrbapp) {
		this.hrbapp = hrbapp;
	}

	public String getHbcapp() {
		return hbcapp;
	}

	public void setHbcapp(String hbcapp) {
		this.hbcapp = hbcapp;
	}

	public String getShrbap() {
		return shrbap;
	}

	public void setShrbap(String shrbap) {
		this.shrbap = shrbap;
	}

	@Override
	public String toString() {
		return "InPropFamDetails [inPropFamDetailsPK=" + inPropFamDetailsPK + ", fmlrel=" + fmlrel + ", fmldob="
				+ fmldob + ", fmlnic=" + fmlnic + ", lockin=" + lockin + ", fmlsex=" + fmlsex + ", fmlshr=" + fmlshr
				+ ", fmlage=" + fmlage + ", cicapp=" + cicapp + ", hrbapp=" + hrbapp + ", hbcapp=" + hbcapp
				+ ", shrbap=" + shrbap + "]";
	}
}
