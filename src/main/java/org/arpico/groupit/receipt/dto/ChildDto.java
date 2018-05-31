package org.arpico.groupit.receipt.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChildDto implements Serializable{
	private Integer childId;
	private String childName;
	private String childNic;
	private Date childDob;
	private String childGender;
	private String childRelation;
	
	public ChildDto() {}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildNic() {
		return childNic;
	}

	public void setChildNic(String childNic) {
		this.childNic = childNic;
	}

	public Date getChildDob() {
		return childDob;
	}

	public void setChildDob(Date childDob) {
		this.childDob = childDob;
	}

	public String getChildGender() {
		return childGender;
	}

	public void setChildGender(String childGender) {
		this.childGender = childGender;
	}

	public String getChildRelation() {
		return childRelation;
	}

	public void setChildRelation(String childRelation) {
		this.childRelation = childRelation;
	}

	
	
}
