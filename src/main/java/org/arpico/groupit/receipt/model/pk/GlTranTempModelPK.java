package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GlTranTempModelPK implements Serializable{

	private String sbuCode;
	private String locCode;
	private String batType;
	private String batcno;
	private String docCod;
	private Integer docNum;
	private Integer linNum;
	private Integer seqNum;

	@Column(name = "SBUCOD")
	public String getSbuCode() {
		return sbuCode;
	}

	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}

	@Column(name = "LOCCOD")
	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	@Column(name = "BATTYP")
	public String getBatType() {
		return batType;
	}

	public void setBatType(String batType) {
		this.batType = batType;
	}

	@Column(name = "BATCNO")
	public String getBatcno() {
		return batcno;
	}

	public void setBatcno(String batcno) {
		this.batcno = batcno;
	}

	@Column(name = "DOCCOD")
	public String getDocCod() {
		return docCod;
	}

	public void setDocCod(String docCod) {
		this.docCod = docCod;
	}

	@Column(name = "DOCNUM")
	public Integer getDocNum() {
		return docNum;
	}

	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}

	@Column(name = "LINNUM")
	public Integer getLinNum() {
		return linNum;
	}

	public void setLinNum(Integer linNum) {
		this.linNum = linNum;
	}

	@Column(name = "SEQNUM")
	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	@Override
	public String toString() {
		return "GlTranTempPkModel [sbuCode=" + sbuCode + ", locCode=" + locCode + ", batType=" + batType + ", batcno="
				+ batcno + ", docCod=" + docCod + ", docNum=" + docNum + ", linNum=" + linNum + ", seqNum=" + seqNum
				+ "]";
	}

}
