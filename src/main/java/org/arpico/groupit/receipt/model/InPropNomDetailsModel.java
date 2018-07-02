package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropNomDetailsModelPK;

@Entity
@Table(name = "inpropnomdetails")
public class InPropNomDetailsModel {

	private InPropNomDetailsModelPK inPropNomDetailsModelPK;
	private Double nomsum;
	private String nomrel;
	private String nomnic;
	private Date nomdob;
	private Date lockin;
	private Double nomshr;
	private String nomtyp;
	private String gurdnm;
	private String gurnic;
	private Date gurdob;
	private String gurrel;
	
	@EmbeddedId
	public InPropNomDetailsModelPK getInPropNomDetailsModelPK() {
		return inPropNomDetailsModelPK;
	}
	public void setInPropNomDetailsModelPK(InPropNomDetailsModelPK inPropNomDetailsModelPK) {
		this.inPropNomDetailsModelPK = inPropNomDetailsModelPK;
	}
	public Double getNomsum() {
		return nomsum;
	}
	public void setNomsum(Double nomsum) {
		this.nomsum = nomsum;
	}
	public String getNomrel() {
		return nomrel;
	}
	public void setNomrel(String nomrel) {
		this.nomrel = nomrel;
	}
	public String getNomnic() {
		return nomnic;
	}
	public void setNomnic(String nomnic) {
		this.nomnic = nomnic;
	}
	public Date getNomdob() {
		return nomdob;
	}
	public void setNomdob(Date nomdob) {
		this.nomdob = nomdob;
	}
	public Date getLockin() {
		return lockin;
	}
	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}
	public Double getNomshr() {
		return nomshr;
	}
	public void setNomshr(Double nomshr) {
		this.nomshr = nomshr;
	}
	public String getNomtyp() {
		return nomtyp;
	}
	public void setNomtyp(String nomtyp) {
		this.nomtyp = nomtyp;
	}
	public String getGurdnm() {
		return gurdnm;
	}
	public void setGurdnm(String gurdnm) {
		this.gurdnm = gurdnm;
	}
	public String getGurnic() {
		return gurnic;
	}
	public void setGurnic(String gurnic) {
		this.gurnic = gurnic;
	}
	public Date getGurdob() {
		return gurdob;
	}
	public void setGurdob(Date gurdob) {
		this.gurdob = gurdob;
	}
	public String getGurrel() {
		return gurrel;
	}
	public void setGurrel(String gurrel) {
		this.gurrel = gurrel;
	}
	@Override
	public String toString() {
		return "InPropNomDetailsModel [inPropNomDetailsModelPK=" + inPropNomDetailsModelPK + ", nomsum=" + nomsum
				+ ", nomrel=" + nomrel + ", nomnic=" + nomnic + ", nomdob=" + nomdob + ", lockin=" + lockin
				+ ", nomshr=" + nomshr + ", nomtyp=" + nomtyp + ", gurdnm=" + gurdnm + ", gurnic=" + gurnic
				+ ", gurdob=" + gurdob + ", gurrel=" + gurrel + "]";
	}
	
}
