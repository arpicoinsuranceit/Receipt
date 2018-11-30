package org.arpico.groupit.receipt.dto;

public class WorkflowProposalChildrenDto {

	private String fullName;
	private String relation;
	private String dob;
	private Integer age;
	private boolean cibc;
	private boolean hcbc;
	private boolean hbc;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isCibc() {
		return cibc;
	}

	public void setCibc(boolean cibc) {
		this.cibc = cibc;
	}

	public boolean isHcbc() {
		return hcbc;
	}

	public void setHcbc(boolean hcbc) {
		this.hcbc = hcbc;
	}

	public boolean isHbc() {
		return hbc;
	}

	public void setHbc(boolean hbc) {
		this.hbc = hbc;
	}

	@Override
	public String toString() {
		return "WorkflowProposalChildrenDto [fullName=" + fullName + ", relation=" + relation + ", dob=" + dob
				+ ", age=" + age + ", cibc=" + cibc + ", hcbc=" + hcbc + ", hbc=" + hbc + "]";
	}

}
