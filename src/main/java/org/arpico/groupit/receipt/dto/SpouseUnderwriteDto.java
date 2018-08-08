package org.arpico.groupit.receipt.dto;

public class SpouseUnderwriteDto {
	
	private String spouseTitle; 
	private String spouseFullName; 
	private String spouseNameInitial; 
	private String spouseNic; 
	private String spouseDob; 
	private Integer spouseAge; 
	private String spouseHeight; 
	private String spouseWeight; 
	private String spouseOccupation; 
	private String spouseAgeAdmitted; 
	private String spouseGender;
	public String getSpouseTitle() {
		return spouseTitle;
	}
	public void setSpouseTitle(String spouseTitle) {
		this.spouseTitle = spouseTitle;
	}
	public String getSpouseFullName() {
		return spouseFullName;
	}
	public void setSpouseFullName(String spouseFullName) {
		this.spouseFullName = spouseFullName;
	}
	public String getSpouseNameInitial() {
		return spouseNameInitial;
	}
	public void setSpouseNameInitial(String spouseNameInitial) {
		this.spouseNameInitial = spouseNameInitial;
	}
	public String getSpouseNic() {
		return spouseNic;
	}
	public void setSpouseNic(String spouseNic) {
		this.spouseNic = spouseNic;
	}
	public String getSpouseDob() {
		return spouseDob;
	}
	public void setSpouseDob(String spouseDob) {
		this.spouseDob = spouseDob;
	}
	public Integer getSpouseAge() {
		return spouseAge;
	}
	public void setSpouseAge(Integer spouseAge) {
		this.spouseAge = spouseAge;
	}
	public String getSpouseHeight() {
		return spouseHeight;
	}
	public void setSpouseHeight(String spouseHeight) {
		this.spouseHeight = spouseHeight;
	}
	public String getSpouseWeight() {
		return spouseWeight;
	}
	public void setSpouseWeight(String spouseWeight) {
		this.spouseWeight = spouseWeight;
	}
	public String getSpouseOccupation() {
		return spouseOccupation;
	}
	public void setSpouseOccupation(String spouseOccupation) {
		this.spouseOccupation = spouseOccupation;
	}
	public String getSpouseAgeAdmitted() {
		return spouseAgeAdmitted;
	}
	public void setSpouseAgeAdmitted(String spouseAgeAdmitted) {
		this.spouseAgeAdmitted = spouseAgeAdmitted;
	}
	public String getSpouseGender() {
		return spouseGender;
	}
	public void setSpouseGender(String spouseGender) {
		this.spouseGender = spouseGender;
	}
	@Override
	public String toString() {
		return "SpouseUnderwriteDto [spouseTitle=" + spouseTitle + ", spouseFullName=" + spouseFullName
				+ ", spouseNameInitial=" + spouseNameInitial + ", spouseNic=" + spouseNic + ", spouseDob=" + spouseDob
				+ ", spouseAge=" + spouseAge + ", spouseHeight=" + spouseHeight + ", spouseWeight=" + spouseWeight
				+ ", spouseOccupation=" + spouseOccupation + ", spouseAgeAdmitted=" + spouseAgeAdmitted
				+ ", spouseGender=" + spouseGender + "]";
	} 
	
	
}
