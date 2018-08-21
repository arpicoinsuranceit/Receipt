package org.arpico.groupit.receipt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InShortPremiumModelPK;

@Entity
@Table(name = "inshort_premium_act_product")
public class InShortPremiumModel implements Serializable{

	private InShortPremiumModelPK inShortPremiumModelPK;
	private String status;
	private String actiby;
	private Date actidt;
	private Double spiamt;

	@EmbeddedId
	public InShortPremiumModelPK getInShortPremiumModelPK() {
		return inShortPremiumModelPK;
	}

	public void setInShortPremiumModelPK(InShortPremiumModelPK inShortPremiumModelPK) {
		this.inShortPremiumModelPK = inShortPremiumModelPK;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActiby() {
		return actiby;
	}

	public void setActiby(String actiby) {
		this.actiby = actiby;
	}

	public Date getActidt() {
		return actidt;
	}

	public void setActidt(Date actidt) {
		this.actidt = actidt;
	}

	public Double getSpiamt() {
		return spiamt;
	}

	public void setSpiamt(Double spiamt) {
		this.spiamt = spiamt;
	}

	@Override
	public String toString() {
		return "InShortPremiumModel [inShortPremiumModelPK=" + inShortPremiumModelPK + ", status=" + status
				+ ", actiby=" + actiby + ", actidt=" + actidt + ", spiamt=" + spiamt + "]";
	}

}
