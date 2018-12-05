package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;
import java.util.Date;

public class CourierDetailsHelperDto {
	
	private Integer courierId;
	private String courierRef;
	private String courierStatus;
	private Date sendDate;
	private String sendBy;
	private String couType;
	private ArrayList<DepartmentHelperDto> departmentHelperDtos;
	
	public Integer getCourierId() {
		return courierId;
	}
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	public String getCourierRef() {
		return courierRef;
	}
	public void setCourierRef(String courierRef) {
		this.courierRef = courierRef;
	}
	public ArrayList<DepartmentHelperDto> getDepartmentHelperDtos() {
		return departmentHelperDtos;
	}
	public void setDepartmentHelperDtos(ArrayList<DepartmentHelperDto> departmentHelperDtos) {
		this.departmentHelperDtos = departmentHelperDtos;
	}
	
	public String getCourierStatus() {
		return courierStatus;
	}
	public void setCourierStatus(String courierStatus) {
		this.courierStatus = courierStatus;
	}
	
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getSendBy() {
		return sendBy;
	}
	public void setSendBy(String sendBy) {
		this.sendBy = sendBy;
	}
	public String getCouType() {
		return couType;
	}
	public void setCouType(String couType) {
		this.couType = couType;
	}
	@Override
	public String toString() {
		return "CourierDetailsHelperDto [courierId=" + courierId + ", courierRef=" + courierRef + ", courierStatus="
				+ courierStatus + ", sendDate=" + sendDate + ", sendBy=" + sendBy + ", couType=" + couType
				+ ", departmentHelperDtos=" + departmentHelperDtos + "]";
	}
	
	

}
