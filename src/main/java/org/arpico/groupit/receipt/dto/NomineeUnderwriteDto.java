package org.arpico.groupit.receipt.dto;

public class NomineeUnderwriteDto{
	private String type;
	private String name; 
	private String relationship;
	private String dob;
	private String nomineeDateofBirth;
	private String share;
	private String nic;
	private String guardianName;
	private String guardianNic;
	private String guardianDOB;
	private String guardianRelation;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getNomineeDateofBirth() {
		return nomineeDateofBirth;
	}
	public void setNomineeDateofBirth(String nomineeDateofBirth) {
		this.nomineeDateofBirth = nomineeDateofBirth;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getGuardianNic() {
		return guardianNic;
	}
	public void setGuardianNic(String guardianNic) {
		this.guardianNic = guardianNic;
	}
	public String getGuardianDOB() {
		return guardianDOB;
	}
	public void setGuardianDOB(String guardianDOB) {
		this.guardianDOB = guardianDOB;
	}
	public String getGuardianRelation() {
		return guardianRelation;
	}
	public void setGuardianRelation(String guardianRelation) {
		this.guardianRelation = guardianRelation;
	}
	@Override
	public String toString() {
		return "NomineeUnderwriteDto [type=" + type + ", name=" + name + ", relationship=" + relationship + ", dob="
				+ dob + ", share=" + share + ", nic=" + nic + ", guardianName=" + guardianName + ", guardianNic="
				+ guardianNic + ", guardianDOB=" + guardianDOB + ", guardianRelation=" + guardianRelation + "]";
	}
	
	
	
}
