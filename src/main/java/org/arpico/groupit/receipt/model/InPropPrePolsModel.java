package org.arpico.groupit.receipt.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InPropPrePolsModelPK;

@Entity
@Table(name = "inpropprepols")
public class InPropPrePolsModel {
	
	private InPropPrePolsModelPK inPropPrePolsModelPK;
	private String prdcod;	
	private String insrer;	
	private Integer sumrkm;	
	private String pplinf;
	
	@EmbeddedId
	public InPropPrePolsModelPK getInPropPrePolsModelPK() {
		return inPropPrePolsModelPK;
	}
	public void setInPropPrePolsModelPK(InPropPrePolsModelPK inPropPrePolsModelPK) {
		this.inPropPrePolsModelPK = inPropPrePolsModelPK;
	}
	public String getPrdcod() {
		return prdcod;
	}
	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}
	public String getInsrer() {
		return insrer;
	}
	public void setInsrer(String insrer) {
		this.insrer = insrer;
	}
	public Integer getSumrkm() {
		return sumrkm;
	}
	public void setSumrkm(Integer sumrkm) {
		this.sumrkm = sumrkm;
	}
	public String getPplinf() {
		return pplinf;
	}
	public void setPplinf(String pplinf) {
		this.pplinf = pplinf;
	}
	@Override
	public String toString() {
		return "InPropPrePolsModel [inPropPrePolsModelPK=" + inPropPrePolsModelPK + ", prdcod=" + prdcod + ", insrer="
				+ insrer + ", sumrkm=" + sumrkm + ", pplinf=" + pplinf + "]";
	}
	
	
	
}
