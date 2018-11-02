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
@Table(name="indepcou")
public class DepartmentCourierModel {
	
	private Integer courierDepartmentId;
	private String courierStatus;
	private String receivedBy;
	private Date receivedDate;
	private String createBy;
	private Date createDate;
	private String token;
	
	private CourierModel courier;
	private DepartmentModel department;
	
	private List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourier=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="depcouid")
	public Integer getCourierDepartmentId() {
		return courierDepartmentId;
	}
	public void setCourierDepartmentId(Integer courierDepartmentId) {
		this.courierDepartmentId = courierDepartmentId;
	}
	@Column(name="coustatus")
	public String getCourierStatus() {
		return courierStatus;
	}
	public void setCourierStatus(String courierStatus) {
		this.courierStatus = courierStatus;
	}
	@Column(name="rcvdby")
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	@Column(name="rcvddt")
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	@Column(name="creaby")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@Column(name="creadt")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false,name="couid")
	public CourierModel getCourier() {
		return courier;
	}
	public void setCourier(CourierModel courier) {
		this.courier = courier;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false,name="depid")
	public DepartmentModel getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}
	
	@OneToMany(mappedBy="departmentCourier")
	@Column(name="subdepdoccouid")
	public List<SubDepartmentDocumentCourierModel> getSubDepartmentDocumentCourier() {
		return subDepartmentDocumentCourier;
	}
	public void setSubDepartmentDocumentCourier(List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourier) {
		this.subDepartmentDocumentCourier = subDepartmentDocumentCourier;
	}
	@Column(name="token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "DepartmentCourierModel [courierDepartmentId=" + courierDepartmentId + ", courierStatus=" + courierStatus
				+ ", receivedBy=" + receivedBy + ", receivedDate=" + receivedDate + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", token=" + token + ", courier=" + courier + ", department="
				+ department + ", subDepartmentDocumentCourier=" + subDepartmentDocumentCourier + "]";
	}
	
	
	
	
	
	

}
