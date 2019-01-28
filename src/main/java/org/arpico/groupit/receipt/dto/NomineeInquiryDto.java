package org.arpico.groupit.receipt.dto;

public class NomineeInquiryDto {
	private String name;
	private String relation;
	private String nic;
	private String dob;
	private String shared;
	private String gName;
	private String gRelation;
	private String gNic;
	private String gDob;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getShared() {
		return shared;
	}
	public void setShared(String shared) {
		this.shared = shared;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgRelation() {
		return gRelation;
	}
	public void setgRelation(String gRelation) {
		this.gRelation = gRelation;
	}
	public String getgNic() {
		return gNic;
	}
	public void setgNic(String gNic) {
		this.gNic = gNic;
	}
	public String getgDob() {
		return gDob;
	}
	public void setgDob(String gDob) {
		this.gDob = gDob;
	}
	
	@Override
	public String toString() {
		return "NomineeInquiryDao [name=" + name + ", relation=" + relation + ", nic=" + nic + ", dob=" + dob
				+ ", shared=" + shared + ", gName=" + gName + ", gRelation=" + gRelation + ", gNic=" + gNic + ", gDob="
				+ gDob + "]";
	}
	
	
	
}
