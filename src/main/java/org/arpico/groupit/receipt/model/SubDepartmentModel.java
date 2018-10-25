package org.arpico.groupit.receipt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="insubdep")
public class SubDepartmentModel {
	
	private Integer subDepId;
	private String sudDepName;
	private Date createDate;
	private String createBy;

	private DepartmentModel depId;
	private List<SubDepartmentDocumentModel> subDepartmentDocument=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subdepid")
	public Integer getSubDepId() {
		return subDepId;
	}
	public void setSubDepId(Integer subDepId) {
		this.subDepId = subDepId;
	}
	@Column(name="subdepnam")
	public String getSudDepName() {
		return sudDepName;
	}
	public void setSudDepName(String sudDepName) {
		this.sudDepName = sudDepName;
	}
	@Column(name="creadt")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="creaby")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@ManyToOne
	@JoinColumn(nullable = false,name="depid")
	public DepartmentModel getDepId() {
		return depId;
	}
	public void setDepId(DepartmentModel depId) {
		this.depId = depId;
	}
	
	@OneToMany(mappedBy="subDepartment")
	@Column(name="subdepdocid")
	public List<SubDepartmentDocumentModel> getSubDepartmentDocument() {
		return subDepartmentDocument;
	}
	public void setSubDepartmentDocument(List<SubDepartmentDocumentModel> subDepartmentDocument) {
		this.subDepartmentDocument = subDepartmentDocument;
	}
	
	@Override
	public String toString() {
		return "SubDepartment [subDepId=" + subDepId + ", sudDepName=" + sudDepName + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", depId=" + depId + ", subDepartmentDocument=" + subDepartmentDocument
				+ "]";
	}
	
	
	
	

}
