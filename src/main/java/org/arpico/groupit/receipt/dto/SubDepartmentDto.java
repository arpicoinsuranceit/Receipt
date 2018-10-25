package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class SubDepartmentDto {
	
	private Integer subDepId;
	private String sudDepName;
	private Date createDate;
	private String createBy;

	private Integer depId;

	public Integer getSubDepId() {
		return subDepId;
	}

	public void setSubDepId(Integer subDepId) {
		this.subDepId = subDepId;
	}

	public String getSudDepName() {
		return sudDepName;
	}

	public void setSudDepName(String sudDepName) {
		this.sudDepName = sudDepName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	@Override
	public String toString() {
		return "SubDepartmentDto [subDepId=" + subDepId + ", sudDepName=" + sudDepName + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", depId=" + depId + "]";
	}
	
	
	
}
