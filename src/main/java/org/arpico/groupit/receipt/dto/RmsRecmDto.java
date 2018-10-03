package org.arpico.groupit.receipt.dto;

public class RmsRecmDto {

	private String docCode;
	private String donNo;
	private String remark;
	private String createDate;
	private Double amtfcu;

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDonNo() {
		return donNo;
	}

	public void setDonNo(String donNo) {
		this.donNo = donNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Double getAmtfcu() {
		return amtfcu;
	}

	public void setAmtfcu(Double amtfcu) {
		this.amtfcu = amtfcu;
	}

	@Override
	public String toString() {
		return "RmsRecmDto [docCode=" + docCode + ", donNo=" + donNo + ", remark=" + remark + ", createDate="
				+ createDate + ", amtfcu=" + amtfcu + "]";
	}

}
