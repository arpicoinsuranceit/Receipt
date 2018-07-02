package org.arpico.groupit.receipt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropLoadingModelPK;
import org.arpico.groupit.receipt.util.AppConstant;

@Entity
@Table(name = "inproploading")
public class InPropLoadingModel implements Serializable{
	
	private InPropLoadingModelPK inPropLoadingPK;
	private String ridnam;
	private Double oculod = AppConstant.ZERO_TWO_DECIMAL;
	private Double ocuval = AppConstant.ZERO_FOR_DECIMAL;
	private Double ocuvmt = AppConstant.ZERO_FOR_DECIMAL;
	private Double ocuvqt = AppConstant.ZERO_FOR_DECIMAL;
	private Double ocuvhy = AppConstant.ZERO_FOR_DECIMAL;
	private Double ocuvyr = AppConstant.ZERO_FOR_DECIMAL;
	private Double subrat = AppConstant.ZERO_FOR_DECIMAL;
	private Double emrate = AppConstant.ZERO_FOR_DECIMAL;
	private Double subval = AppConstant.ZERO_FOR_DECIMAL;
	private Double subvmt = AppConstant.ZERO_FOR_DECIMAL;
	private Double subvqt = AppConstant.ZERO_FOR_DECIMAL;
	private Double subvhy = AppConstant.ZERO_FOR_DECIMAL;
	private Double subvyr = AppConstant.ZERO_FOR_DECIMAL;
	private Double ratmil = AppConstant.ZERO_FOR_DECIMAL;
	private Double ratval = AppConstant.ZERO_FOR_DECIMAL;
	private Double ratvmt = AppConstant.ZERO_FOR_DECIMAL;
	private Double ratvqt = AppConstant.ZERO_FOR_DECIMAL;
	private Double ratvhy = AppConstant.ZERO_FOR_DECIMAL;
	private Double ratvyr = AppConstant.ZERO_FOR_DECIMAL;
	private Date lockin;
	private String instyp;
	private String ridtyp;
	private Integer grdord;
	
	@EmbeddedId
	public InPropLoadingModelPK getInPropLoadingPK() {
		return inPropLoadingPK;
	}
	public void setInPropLoadingPK(InPropLoadingModelPK inPropLoadingPK) {
		this.inPropLoadingPK = inPropLoadingPK;
	}
	public String getRidnam() {
		return ridnam;
	}
	public void setRidnam(String ridnam) {
		this.ridnam = ridnam;
	}
	public Double getOculod() {
		return oculod;
	}
	public void setOculod(Double oculod) {
		this.oculod = oculod;
	}
	public Double getOcuval() {
		return ocuval;
	}
	public void setOcuval(Double ocuval) {
		this.ocuval = ocuval;
	}
	public Double getOcuvmt() {
		return ocuvmt;
	}
	public void setOcuvmt(Double ocuvmt) {
		this.ocuvmt = ocuvmt;
	}
	public Double getOcuvqt() {
		return ocuvqt;
	}
	public void setOcuvqt(Double ocuvqt) {
		this.ocuvqt = ocuvqt;
	}
	public Double getOcuvhy() {
		return ocuvhy;
	}
	public void setOcuvhy(Double ocuvhy) {
		this.ocuvhy = ocuvhy;
	}
	public Double getOcuvyr() {
		return ocuvyr;
	}
	public void setOcuvyr(Double ocuvyr) {
		this.ocuvyr = ocuvyr;
	}
	public Double getSubrat() {
		return subrat;
	}
	public void setSubrat(Double subrat) {
		this.subrat = subrat;
	}
	public Double getEmrate() {
		return emrate;
	}
	public void setEmrate(Double emrate) {
		this.emrate = emrate;
	}
	public Double getSubval() {
		return subval;
	}
	public void setSubval(Double subval) {
		this.subval = subval;
	}
	public Double getSubvmt() {
		return subvmt;
	}
	public void setSubvmt(Double subvmt) {
		this.subvmt = subvmt;
	}
	public Double getSubvqt() {
		return subvqt;
	}
	public void setSubvqt(Double subvqt) {
		this.subvqt = subvqt;
	}
	public Double getSubvhy() {
		return subvhy;
	}
	public void setSubvhy(Double subvhy) {
		this.subvhy = subvhy;
	}
	public Double getSubvyr() {
		return subvyr;
	}
	public void setSubvyr(Double subvyr) {
		this.subvyr = subvyr;
	}
	public Double getRatmil() {
		return ratmil;
	}
	public void setRatmil(Double ratmil) {
		this.ratmil = ratmil;
	}
	public Double getRatval() {
		return ratval;
	}
	public void setRatval(Double ratval) {
		this.ratval = ratval;
	}
	public Double getRatvmt() {
		return ratvmt;
	}
	public void setRatvmt(Double ratvmt) {
		this.ratvmt = ratvmt;
	}
	public Double getRatvqt() {
		return ratvqt;
	}
	public void setRatvqt(Double ratvqt) {
		this.ratvqt = ratvqt;
	}
	public Double getRatvhy() {
		return ratvhy;
	}
	public void setRatvhy(Double ratvhy) {
		this.ratvhy = ratvhy;
	}
	public Double getRatvyr() {
		return ratvyr;
	}
	public void setRatvyr(Double ratvyr) {
		this.ratvyr = ratvyr;
	}
	public Date getLockin() {
		return lockin;
	}
	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}
	public String getInstyp() {
		return instyp;
	}
	public void setInstyp(String instyp) {
		this.instyp = instyp;
	}
	public String getRidtyp() {
		return ridtyp;
	}
	public void setRidtyp(String ridtyp) {
		this.ridtyp = ridtyp;
	}
	public Integer getGrdord() {
		return grdord;
	}
	public void setGrdord(Integer grdord) {
		this.grdord = grdord;
	}
}
