package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;
import java.util.Date;

public class DepartmentHelperDto {
	
	private String depName;
	private String depCouStatus;
	private String rcvdBy;
	private Date rcvdDate;
	private ArrayList<SubDepartmentHelperDto> subDepartmentHelperDtos;
	
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	public String getDepCouStatus() {
		return depCouStatus;
	}
	public void setDepCouStatus(String depCouStatus) {
		this.depCouStatus = depCouStatus;
	}
	
	public String getRcvdBy() {
		return rcvdBy;
	}
	public void setRcvdBy(String rcvdBy) {
		this.rcvdBy = rcvdBy;
	}
	public Date getRcvdDate() {
		return rcvdDate;
	}
	public void setRcvdDate(Date rcvdDate) {
		this.rcvdDate = rcvdDate;
	}
	public ArrayList<SubDepartmentHelperDto> getSubDepartmentHelperDtos() {
		return subDepartmentHelperDtos;
	}
	public void setSubDepartmentHelperDtos(ArrayList<SubDepartmentHelperDto> subDepartmentHelperDtos) {
		this.subDepartmentHelperDtos = subDepartmentHelperDtos;
	}
	@Override
	public String toString() {
		return "DepartmentHelperDto [depName=" + depName + ", depCouStatus=" + depCouStatus
				+ ", subDepartmentHelperDtos=" + subDepartmentHelperDtos + "]";
	}
	
}
