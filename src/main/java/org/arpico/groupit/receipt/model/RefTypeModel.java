package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="incoureftype")
public class RefTypeModel {
	
	private Integer refTypeId;
	private String refTypeName;
	private String creBy;
	private Date creDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="reftypeid")
	public Integer getRefTypeId() {
		return refTypeId;
	}
	public void setRefTypeId(Integer refTypeId) {
		this.refTypeId = refTypeId;
	}
	@Column(name="reftypename")
	public String getRefTypeName() {
		return refTypeName;
	}
	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}
	@Column(name="creby")
	public String getCreBy() {
		return creBy;
	}
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}
	@Column(name="credat")
	public Date getCreDate() {
		return creDate;
	}
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}
	@Override
	public String toString() {
		return "RefTypeModel [refTypeId=" + refTypeId + ", refTypeName=" + refTypeName + ", creBy=" + creBy
				+ ", creDate=" + creDate + "]";
	}
	
	

}
