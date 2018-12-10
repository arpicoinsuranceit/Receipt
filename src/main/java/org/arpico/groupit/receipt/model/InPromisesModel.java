package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INPROMISES")
public class InPromisesModel {

	private String sbuCode;
	private Integer id;
	private String pprno;
	private String policyNo;
	private String locCode;
	private Double amount;
	private Date dueDate;
	private Date settleDate;
	private String phoneNo;
	private Integer active;
	private String custName;
	private String custNic;
	private String remark;
	private String payType;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;

	@Column(name = "sbucod")
	public String getSbuCode() {
		return sbuCode;
	}

	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "promid")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "prpnum")
	public String getPprno() {
		return pprno;
	}

	public void setPprno(String pprno) {
		this.pprno = pprno;
	}

	@Column(name = "polnum")
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	@Column(name = "loccod")
	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "duedat")
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "setdat")
	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	@Column(name = "phnnum")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "active")
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "ppdnam")
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "ppdnic")
	public String getCustNic() {
		return custNic;
	}

	public void setCustNic(String custNic) {
		this.custNic = custNic;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "paytyp")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "creaby")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "creadt")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "modiby")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "modidt")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "InPromisesModel [sbuCode=" + sbuCode + ", id=" + id + ", pprno=" + pprno + ", policyNo=" + policyNo
				+ ", locCode=" + locCode + ", amount=" + amount + ", dueDate=" + dueDate + ", settleDate=" + settleDate
				+ ", phoneNo=" + phoneNo + ", active=" + active + ", custName=" + custName + ", custNic=" + custNic
				+ ", remark=" + remark + ", payType=" + payType + ", createBy=" + createBy + ", createDate="
				+ createDate + ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}

}
