package org.arpico.groupit.receipt.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RmsDocTxndModelPK implements Serializable {

	private String sbuCode;

	private String locCode;

	private String docCode;

	private Integer docNo;

	private Integer seqNo;

	@Column(name = "SBU_CODE")
	public String getSbuCode() {
		return sbuCode;
	}

	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}

	@Column(name = "LOC_CODE")
	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	@Column(name = "DOC_CODE")
	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	@Column(name = "DOC_NO")
	public Integer getDocNo() {
		return docNo;
	}

	public void setDocNo(Integer docNo) {
		this.docNo = docNo;
	}

	@Column(name = "SEQ_NO")
	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	@Override
	public String toString() {
		return "RmsDocTxndModelPK [sbuCode=" + sbuCode + ", locCode=" + locCode + ", docCode=" + docCode + ", docNo="
				+ docNo + ", seqNo=" + seqNo + "]";
	}

}
