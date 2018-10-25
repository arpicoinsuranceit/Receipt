package org.arpico.groupit.receipt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="indep")
public class DepartmentModel {
	
	private Integer departmentId;
	private String departmentName;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	
	private List<DepartmentCourierModel> departmentCourier=new ArrayList<>();
	private List<SubDepartmentModel>  subDepartments=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="depid")
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	@Column(name="depnam")
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	@Column(name="moddt")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name="modby")
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	@OneToMany(mappedBy="depId")
	@Column(name="subdepid")
	public List<SubDepartmentModel> getSubDepartments() {
		return subDepartments;
	}
	public void setSubDepartments(List<SubDepartmentModel> subDepartments) {
		this.subDepartments = subDepartments;
	}
	
	@OneToMany(mappedBy="department")
	@Column(name="depcouid")
	public List<DepartmentCourierModel> getDepartmentCourier() {
		return departmentCourier;
	}
	public void setDepartmentCourier(List<DepartmentCourierModel> departmentCourier) {
		this.departmentCourier = departmentCourier;
	}
	@Override
	public String toString() {
		return "DepartmentModel [departmentId=" + departmentId + ", departmentName=" + departmentName + ", createDate="
				+ createDate + ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy
				+ ", departmentCourier=" + departmentCourier + ", subDepartments=" + subDepartments + "]";
	}
	
	
	

}
