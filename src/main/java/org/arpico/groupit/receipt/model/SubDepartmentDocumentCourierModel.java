package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="insubdepdoccou")
public class SubDepartmentDocumentCourierModel {
	
	private Integer subDepartmentDocumentCourierId;
	private String subDepDocCouToken;
	private String underwriterEmail;
	private String remark;
	private String status;
	private String receivedBy;
	private Date receivedDate;
	private String referenceNo;
	private String referenceType;
	private String branchCode;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	private String currentUser;
	
	private SubDepartmentDocumentModel subDepartmentDocument;
	private DepartmentCourierModel departmentCourier;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subdepdoccouid")
	public Integer getSubDepartmentDocumentCourierId() {
		return subDepartmentDocumentCourierId;
	}
	public void setSubDepartmentDocumentCourierId(Integer subDepartmentDocumentCourierId) {
		this.subDepartmentDocumentCourierId = subDepartmentDocumentCourierId;
	}
	@Column(name="token")
	public String getSubDepDocCouToken() {
		return subDepDocCouToken;
	}
	public void setSubDepDocCouToken(String subDepDocCouToken) {
		this.subDepDocCouToken = subDepDocCouToken;
	}
	@Column(name="uwemail")
	public String getUnderwriterEmail() {
		return underwriterEmail;
	}
	public void setUnderwriterEmail(String underwriterEmail) {
		this.underwriterEmail = underwriterEmail;
	}
	@Column(name="rmk")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="rcvdby")
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	@Column(name="rcvddat")
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	@Column(name="refno")
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	@Column(name="reftype")
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	@Column(name="brncode")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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
	@Column(name="user")
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false,name="subdepdocid")
	public SubDepartmentDocumentModel getSubDepartmentDocument() {
		return subDepartmentDocument;
	}
	public void setSubDepartmentDocument(SubDepartmentDocumentModel subDepartmentDocument) {
		this.subDepartmentDocument = subDepartmentDocument;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false,name="depcouid")
	public DepartmentCourierModel getDepartmentCourier() {
		return departmentCourier;
	}
	public void setDepartmentCourier(DepartmentCourierModel departmentCourier) {
		this.departmentCourier = departmentCourier;
	}
	
	@Override
	public String toString() {
		return "SubDepartmentDocumentCourierModel [subDepartmentDocumentCourierId=" + subDepartmentDocumentCourierId
				+ ", subDepDocCouToken=" + subDepDocCouToken + ", underwriterEmail=" + underwriterEmail + ", remark="
				+ remark + ", status=" + status + ", receivedBy=" + receivedBy + ", receivedDate=" + receivedDate
				+ ", referenceNo=" + referenceNo + ", branchCode=" + branchCode + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy + ", currentUser="
				+ currentUser + ", subDepartmentDocument=" + subDepartmentDocument + ", departmentCourier="
				+ departmentCourier + "]";
	}
	
	
	
	
}
