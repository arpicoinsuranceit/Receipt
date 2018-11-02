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
@Table(name="insubdepdoc")
public class SubDepartmentDocumentModel {
	
	private Integer subDepDocId;
	private boolean active;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	
	private SubDepartmentModel subDepartment;
	private DocumentTypeModel documentType;
	private List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourier=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subdepdocid")
	public Integer getSubDepDocId() {
		return subDepDocId;
	}
	public void setSubDepDocId(Integer subDepDocId) {
		this.subDepDocId = subDepDocId;
	}
	@Column(name="isactive")
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	
	@ManyToOne
	@JoinColumn(nullable = false,name="subdepid")
	public SubDepartmentModel getSubDepartment() {
		return subDepartment;
	}
	public void setSubDepartment(SubDepartmentModel subDepartment) {
		this.subDepartment = subDepartment;
	}
	
	@ManyToOne
	@JoinColumn(nullable = false,name="doctypeid")
	public DocumentTypeModel getDocumentType() {
		return documentType;
	}
	public void setDocumentType(DocumentTypeModel documentType) {
		this.documentType = documentType;
	}
	
	@OneToMany(mappedBy="subDepartmentDocument")
	@Column(name="subdepdoccouid")
	public List<SubDepartmentDocumentCourierModel> getSubDepartmentDocumentCourier() {
		return subDepartmentDocumentCourier;
	}
	public void setSubDepartmentDocumentCourier(List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourier) {
		this.subDepartmentDocumentCourier = subDepartmentDocumentCourier;
	}
	
	@Override
	public String toString() {
		return "SubDepartmentDocument [subDepDocId=" + subDepDocId + ", active=" + active + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy
				+ ", subDepartment=" + subDepartment + ", documentType=" + documentType + "]";
	}
	
	

}
