package org.arpico.groupit.receipt.dto;

public class ChildInqDto {
	
	private String name;
	private String relation;
	private String dob;
	private Integer age;
	private String sex;
	private String cibc;
	private String hrbc;
	private String hbc;
	private String suhrbc;
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCibc() {
		return cibc;
	}
	public void setCibc(String cibc) {
		this.cibc = cibc;
	}
	public String getHrbc() {
		return hrbc;
	}
	public void setHrbc(String hrbc) {
		this.hrbc = hrbc;
	}
	public String getHbc() {
		return hbc;
	}
	public void setHbc(String hbc) {
		this.hbc = hbc;
	}
	public String getSuhrbc() {
		return suhrbc;
	}
	public void setSuhrbc(String suhrbc) {
		this.suhrbc = suhrbc;
	}
	@Override
	public String toString() {
		return "ChildInqDto [name=" + name + ", relation=" + relation + ", dob=" + dob + ", age=" + age + ", sex=" + sex
				+ ", cibc=" + cibc + ", hrbc=" + hrbc + ", hbc=" + hbc + ", suhrbc=" + suhrbc + "]";
	}
	
	

}
