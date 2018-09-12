package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RmsRecmModelPK implements Serializable {

	@Column(name = "SBU_CODE")
	private String sbuCode;

	@Column(name = "LOC_CODE")
	private String locCode;

	@Column(name = "DOC_CODE")
	private String docCode;

	@Column(name = "DOC_NO")
	private Integer docNo;

	public String getSbuCode() {
		return sbuCode;
	}

	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public Integer getDocNo() {
		return docNo;
	}

	public void setDocNo(Integer docNo) {
		this.docNo = docNo;
	}

	@Override
	public String toString() {
		return "RmsRecmModelPK [sbuCode=" + sbuCode + ", locCode=" + locCode + ", docCode=" + docCode + ", docNo="
				+ docNo + "]";
	}

}
