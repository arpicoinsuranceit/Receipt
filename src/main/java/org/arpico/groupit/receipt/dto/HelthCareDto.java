package org.arpico.groupit.receipt.dto;

public class HelthCareDto {
	private String cadsdt;
	private String remark;

	public String getCadsdt() {
		return cadsdt;
	}

	public void setCadsdt(String cadsdt) {
		this.cadsdt = cadsdt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "HelthCare [cadsdt=" + cadsdt + ", remark=" + remark + "]";
	}
}
