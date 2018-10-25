package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;

public class CourierDetailsHelperDto {
	
	private Integer courierId;
	private String courierRef;
	private String courierStatus;
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
	@Override
	public String toString() {
		return "CourierDetailsHelperDto [courierId=" + courierId + ", courierRef=" + courierRef + ", courierStatus="
				+ courierStatus + ", departmentHelperDtos=" + departmentHelperDtos + "]";
	}
	

}
