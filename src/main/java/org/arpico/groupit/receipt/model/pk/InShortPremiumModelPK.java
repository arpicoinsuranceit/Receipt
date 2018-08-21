package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InShortPremiumModelPK implements Serializable{

	private String sbucod;
	private String prdcod;

	public String getSbucod() {
		return sbucod;
	}

	public void setSbucod(String sbucod) {
		this.sbucod = sbucod;
	}

	public String getPrdcod() {
		return prdcod;
	}

	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}

	@Override
	public String toString() {
		return "InShortPremiumModelPK [sbucod=" + sbucod + ", prdcod=" + prdcod + "]";
	}

}
