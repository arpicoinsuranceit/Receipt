package org.arpico.groupit.receipt.dto;

public class MedicalReqDto {
	private String testCode;
	private String testName;
	private String origin;
	private String recived;
	private String hospital;
	private String testDate;
	private Double payAmount;
	private String payStatus;
	private String additionalNotes;
	private String type;

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getRecived() {
		return recived;
	}

	public void setRecived(String recived) {
		this.recived = recived;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MedicalReqDto [testCode=" + testCode + ", testName=" + testName + ", origin=" + origin + ", recived="
				+ recived + ", hospital=" + hospital + ", testDate=" + testDate + ", payAmount=" + payAmount
				+ ", payStatus=" + payStatus + ", additionalNotes=" + additionalNotes + "]";
	}

}
