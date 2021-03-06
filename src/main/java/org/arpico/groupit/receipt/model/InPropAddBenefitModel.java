package org.arpico.groupit.receipt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropAddBenefitModelPK;
import org.arpico.groupit.receipt.util.AppConstant;

@Entity
@Table(name = "inpropaddbenefit")
public class InPropAddBenefitModel implements Serializable{
	private InPropAddBenefitModelPK inPropAddBenefitPK;
	private String ridnam;
	private Integer ridtrm;
	private Double sumasu = AppConstant.ZERO_FOR_DECIMAL;
	private Double rdrprm = AppConstant.ZERO_FOR_DECIMAL;
	private Date lockin;
	private Double prmmth = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmqat = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmhlf = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmyer = AppConstant.ZERO_FOR_DECIMAL;
	private String instyp;
	private Integer disord;
	private String ridtyp;
	private String prmonl;
	private Integer agemin;
	private Integer agemax;
	private Integer grdord;
	private String grprid;
	private Date expdat;
	private String medgrd;
	
	@EmbeddedId
	public InPropAddBenefitModelPK getInPropAddBenefitPK() {
		return inPropAddBenefitPK;
	}
	public void setInPropAddBenefitPK(InPropAddBenefitModelPK inPropAddBenefitPK) {
		this.inPropAddBenefitPK = inPropAddBenefitPK;
	}

	public String getRidnam() {
		return ridnam;
	}
	public void setRidnam(String ridnam) {
		this.ridnam = ridnam;
	}
	public Integer getRidtrm() {
		return ridtrm;
	}
	public void setRidtrm(Integer ridtrm) {
		this.ridtrm = ridtrm;
	}
	public Double getSumasu() {
		return sumasu;
	}
	public void setSumasu(Double sumasu) {
		this.sumasu = sumasu;
	}
	public Double getRdrprm() {
		return rdrprm;
	}
	public void setRdrprm(Double rdrprm) {
		this.rdrprm = rdrprm;
	}
	public Date getLockin() {
		return lockin;
	}
	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}
	public Double getPrmmth() {
		return prmmth;
	}
	public void setPrmmth(Double prmmth) {
		this.prmmth = prmmth;
	}
	public Double getPrmqat() {
		return prmqat;
	}
	public void setPrmqat(Double prmqat) {
		this.prmqat = prmqat;
	}
	public Double getPrmhlf() {
		return prmhlf;
	}
	public void setPrmhlf(Double prmhlf) {
		this.prmhlf = prmhlf;
	}
	public Double getPrmyer() {
		return prmyer;
	}
	public void setPrmyer(Double prmyer) {
		this.prmyer = prmyer;
	}
	public String getInstyp() {
		return instyp;
	}
	public void setInstyp(String instyp) {
		this.instyp = instyp;
	}
	public Integer getDisord() {
		return disord;
	}
	public void setDisord(Integer disord) {
		this.disord = disord;
	}
	public String getRidtyp() {
		return ridtyp;
	}
	public void setRidtyp(String ridtyp) {
		this.ridtyp = ridtyp;
	}
	public String getPrmonl() {
		return prmonl;
	}
	public void setPrmonl(String prmonl) {
		this.prmonl = prmonl;
	}
	public Integer getAgemin() {
		return agemin;
	}
	public void setAgemin(Integer agemin) {
		this.agemin = agemin;
	}
	public Integer getAgemax() {
		return agemax;
	}
	public void setAgemax(Integer agemax) {
		this.agemax = agemax;
	}
	public Integer getGrdord() {
		return grdord;
	}
	public void setGrdord(Integer grdord) {
		this.grdord = grdord;
	}
	public String getGrprid() {
		return grprid;
	}
	public void setGrprid(String grprid) {
		this.grprid = grprid;
	}
	public Date getExpdat() {
		return expdat;
	}
	public void setExpdat(Date expdat) {
		this.expdat = expdat;
	}
	public String getMedgrd() {
		return medgrd;
	}
	public void setMedgrd(String medgrd) {
		this.medgrd = medgrd;
	}
	@Override
	public String toString() {
		return "InPropAddBenefitModel [inPropAddBenefitPK=" + inPropAddBenefitPK + ", ridnam=" + ridnam + ", ridtrm="
				+ ridtrm + ", sumasu=" + sumasu + ", rdrprm=" + rdrprm + ", lockin=" + lockin + ", prmmth=" + prmmth
				+ ", prmqat=" + prmqat + ", prmhlf=" + prmhlf + ", prmyer=" + prmyer + ", instyp=" + instyp
				+ ", disord=" + disord + ", ridtyp=" + ridtyp + ", prmonl=" + prmonl + ", agemin=" + agemin
				+ ", agemax=" + agemax + ", grdord=" + grdord + ", grprid=" + grprid + ", expdat=" + expdat
				+ ", medgrd=" + medgrd + "]";
	}
	
	
	
}
