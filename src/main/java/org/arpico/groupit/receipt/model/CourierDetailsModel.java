package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incourierdetails")
public class CourierDetailsModel {
	
	private Integer courierDetailsId;
	private String referenceNumber;
	private String polNo;
	private String prpNo;
	private String agentCode;
	private String branch;
	private String underwriterEmail;
	private String department;
	private String docType;
	private String remarks;
	private String status;
	private String user;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="coudetid")
	public Integer getCourierDetailsId() {
		return courierDetailsId;
	}
	public void setCourierDetailsId(Integer courierDetailsId) {
		this.courierDetailsId = courierDetailsId;
	}
	
	@Column(name="refnum")
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	@Column(name="polnum")
	public String getPolNo() {
		return polNo;
	}
	public void setPolNo(String polNo) {
		this.polNo = polNo;
	}
	
	@Column(name="pprnum")
	public String getPrpNo() {
		return prpNo;
	}
	public void setPrpNo(String prpNo) {
		this.prpNo = prpNo;
	}
	
	@Column(name="agncod")
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	@Column(name="brncod")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	@Column(name="unemail")
	public String getUnderwriterEmail() {
		return underwriterEmail;
	}
	public void setUnderwriterEmail(String underwriterEmail) {
		this.underwriterEmail = underwriterEmail;
	}
	
	@Column(name="depnam")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name="doctyp")
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	@Column(name="rmk")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="user")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	
	@Override
	public String toString() {
		return "CourierDetailsModel [courierDetailsId=" + courierDetailsId + ", referenceNumber=" + referenceNumber
				+ ", polNo=" + polNo + ", prpNo=" + prpNo + ", agentCode=" + agentCode + ", branch=" + branch
				+ ", underwriterEmail=" + underwriterEmail + ", department=" + department + ", docType=" + docType
				+ ", remarks=" + remarks + ", status=" + status + ", user=" + user + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy + "]";
	}
	
	
	
	
	
	
}
