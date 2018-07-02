package org.arpico.groupit.receipt.dto;

public class MedicalRequirementsDto {

	private String mediCode;
	private String mediName;
	private String insType;
	private String addNote;
	public String getMediCode() {
		return mediCode;
	}
	public void setMediCode(String mediCode) {
		this.mediCode = mediCode;
	}
	public String getMediName() {
		return mediName;
	}
	public void setMediName(String mediName) {
		this.mediName = mediName;
	}
	public String getInsType() {
		return insType;
	}
	public void setInsType(String insType) {
		this.insType = insType;
	}
	public String getAddNote() {
		return addNote;
	}
	public void setAddNote(String addNote) {
		this.addNote = addNote;
	}
	@Override
	public String toString() {
		return "MedicalRequirementsDto [mediCode=" + mediCode + ", mediName=" + mediName + ", insType=" + insType
				+ ", addNote=" + addNote + "]";
	}
}
