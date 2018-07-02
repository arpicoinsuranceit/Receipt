package org.arpico.groupit.receipt.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropSchedulesModelPK;

@Entity
@Table(name = "inpropschedules")
public class InPropSchedulesModel implements Serializable {

	private InPropSchedulesModelPK inPropSchedulesPK;
	private Integer outyer;
	private Double outsum;
	private Double lonred;
	private Double prmrat;
	private Double premum;
	
	@EmbeddedId
	public InPropSchedulesModelPK getInPropSchedulesPK() {
		return inPropSchedulesPK;
	}
	public void setInPropSchedulesPK(InPropSchedulesModelPK inPropSchedulesPK) {
		this.inPropSchedulesPK = inPropSchedulesPK;
	}
	public Integer getOutyer() {
		return outyer;
	}
	public void setOutyer(Integer outyer) {
		this.outyer = outyer;
	}
	public Double getOutsum() {
		return outsum;
	}
	public void setOutsum(Double outsum) {
		this.outsum = outsum;
	}
	public Double getLonred() {
		return lonred;
	}
	public void setLonred(Double lonred) {
		this.lonred = lonred;
	}
	public Double getPrmrat() {
		return prmrat;
	}
	public void setPrmrat(Double prmrat) {
		this.prmrat = prmrat;
	}
	public Double getPremum() {
		return premum;
	}
	public void setPremum(Double premum) {
		this.premum = premum;
	}
	
}
