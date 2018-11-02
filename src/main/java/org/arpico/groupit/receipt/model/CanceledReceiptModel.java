package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inreceiptauth")
public class CanceledReceiptModel {
	
	private String sbuCode;
	private String locCode;
	private String receiptNo;
	private String polNum;
	private String pprNum;
	private String reason;
	private String status;
	private String requestBy;
	private Date requestDate;
	private String gmApprovedBy;
	private Date gmApprovedDate;
	private String hoApprovedBy;
	private Date hoApprovedDate;
	private Date createDate;
	private String createBy;
	private String modifyBy;
	private Date modifyDate;
	private String hoRemark;
	private String gmRemark;
	private Double amount;
	private String docCode;
	
	@Column(name="sbucod")
	public String getSbuCode() {
		return sbuCode;
	}
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}
	
	@Column(name="loccod")
	public String getLocCode() {
		return locCode;
	}
	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}
	
	@Id
	@Column(name="docnum")
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	
	@Column(name="polnum")
	public String getPolNum() {
		return polNum;
	}
	public void setPolNum(String polNum) {
		this.polNum = polNum;
	}
	
	@Column(name="pprnum")
	public String getPprNum() {
		return pprNum;
	}
	public void setPprNum(String pprNum) {
		this.pprNum = pprNum;
	}
	
	@Column(name="reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="rqstby")
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	
	@Column(name="rqstdt")
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	@Column(name="gmapby")
	public String getGmApprovedBy() {
		return gmApprovedBy;
	}
	public void setGmApprovedBy(String gmApprovedBy) {
		this.gmApprovedBy = gmApprovedBy;
	}
	
	@Column(name="gmapdt")
	public Date getGmApprovedDate() {
		return gmApprovedDate;
	}
	public void setGmApprovedDate(Date gmApprovedDate) {
		this.gmApprovedDate = gmApprovedDate;
	}
	
	@Column(name="hoapby")
	public String getHoApprovedBy() {
		return hoApprovedBy;
	}
	public void setHoApprovedBy(String hoApprovedBy) {
		this.hoApprovedBy = hoApprovedBy;
	}
	
	@Column(name="hoapdt")
	public Date getHoApprovedDate() {
		return hoApprovedDate;
	}
	public void setHoApprovedDate(Date hoApprovedDate) {
		this.hoApprovedDate = hoApprovedDate;
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
	
	@Column(name="modby")
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	@Column(name="moddt")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name="hormk")
	public String getHoRemark() {
		return hoRemark;
	}
	public void setHoRemark(String hoRemark) {
		this.hoRemark = hoRemark;
	}
	
	@Column(name="gmrmk")
	public String getGmRemark() {
		return gmRemark;
	}
	public void setGmRemark(String gmRemark) {
		this.gmRemark = gmRemark;
	}
	
	@Column(name="amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Column(name="doccod")
	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	@Override
	public String toString() {
		return "CanceledReceiptModel [sbuCode=" + sbuCode + ", locCode=" + locCode + ", receiptNo=" + receiptNo
				+ ", polNum=" + polNum + ", pprNum=" + pprNum + ", reason=" + reason + ", status=" + status
				+ ", requestBy=" + requestBy + ", requestDate=" + requestDate + ", gmApprovedBy=" + gmApprovedBy
				+ ", gmApprovedDate=" + gmApprovedDate + ", hoApprovedBy=" + hoApprovedBy + ", hoApprovedDate="
				+ hoApprovedDate + ", createDate=" + createDate + ", createBy=" + createBy + ", modifyBy=" + modifyBy
				+ ", modifyDate=" + modifyDate + ", hoRemark=" + hoRemark + ", gmRemark=" + gmRemark + ", amount="
				+ amount + ", docCode=" + docCode + "]";
	}
	


}
