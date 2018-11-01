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
@Table(name="indoctype")
public class DocumentTypeModel {
	
	private Integer docTypeId;
	private String docName;
	private String docTypeCode;
	private boolean active;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	private Integer parent;
	private Integer isDoc;
	
	private List<SubDepartmentDocumentModel> subDepartmentDocument=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="doctypeid")
	public Integer getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}
	@Column(name="doctypenam")
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	@Column(name="doctypecod")
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
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
	
	@OneToMany(mappedBy="documentType")
	@Column(name="subdepdocid")
	public List<SubDepartmentDocumentModel> getSubDepartmentDocument() {
		return subDepartmentDocument;
	}
	public void setSubDepartmentDocument(List<SubDepartmentDocumentModel> subDepartmentDocument) {
		this.subDepartmentDocument = subDepartmentDocument;
	}
	@Column(name="parent")
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	@Column(name="isDoc")
	public Integer getIsDoc() {
		return isDoc;
	}
	public void setIsDoc(Integer isDoc) {
		this.isDoc = isDoc;
	}
	@Override
	public String toString() {
		return "DocumentTypeModel [docTypeId=" + docTypeId + ", docName=" + docName + ", active=" + active
				+ ", createDate=" + createDate + ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy="
				+ modifyBy + ", parent=" + parent + ", isDoc=" + isDoc + ", subDepartmentDocument="
				+ subDepartmentDocument + "]";
	}
	

}
