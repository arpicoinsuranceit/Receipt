package org.arpico.groupit.receipt.dto;

import java.io.Serializable;
import java.util.Date;

public class NomineeDto{
	private Integer id;
	private String nomineeName;
	private Integer age;
	private Date nomineeDob;
	private String relation;
	private String nomineeDateofBirth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getNomineeDob() {
		return nomineeDob;
	}

	public void setNomineeDob(Date nomineeDob) {
		this.nomineeDob = nomineeDob;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getNomineeDateofBirth() {
		return nomineeDateofBirth;
	}

	public void setNomineeDateofBirth(String nomineeDateofBirth) {
		this.nomineeDateofBirth = nomineeDateofBirth;
	}
	
}
