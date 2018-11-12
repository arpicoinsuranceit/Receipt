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
@Table(name="incourier")
public class CourierModel {
	
	private Integer courierId;
	private String token;
	private String branchCode;
	private String courierStatus;
	private String remark;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;
	private String toBranch;
	
	private List<DepartmentCourierModel> departmentCourier=new ArrayList<>();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="couid")
	public Integer getCourierId() {
		return courierId;
	}
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	@Column(name="token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Column(name="brncode")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	@Column(name="coustatus")
	public String getCourierStatus() {
		return courierStatus;
	}
	public void setCourierStatus(String courierStatus) {
		this.courierStatus = courierStatus;
	}
	@Column(name="courmk")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	@Column(name="tobrncod")
	public String getToBranch() {
		return toBranch;
	}
	public void setToBranch(String toBranch) {
		this.toBranch = toBranch;
	}
	@OneToMany(mappedBy="courier")
	@Column(name="depcouid")
	public List<DepartmentCourierModel> getDepartmentCourier() {
		return departmentCourier;
	}
	public void setDepartmentCourier(List<DepartmentCourierModel> departmentCourier) {
		this.departmentCourier = departmentCourier;
	}
	@Override
	public String toString() {
		return "CourierModel [courierId=" + courierId + ", token=" + token + ", branchCode=" + branchCode
				+ ", courierStatus=" + courierStatus + ", remark=" + remark + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy + ", toBranch="
				+ toBranch + ", departmentCourier=" + departmentCourier + "]";
	}
	
	

}
